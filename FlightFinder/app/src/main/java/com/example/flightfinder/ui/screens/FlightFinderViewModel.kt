package com.example.flightfinder.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.flightfinder.Application
import com.example.flightfinder.data.datasource.local.db.entity.Airport
import com.example.flightfinder.data.datasource.local.db.entity.Favorite
import com.example.flightfinder.data.repository.FlightsRepository
import com.example.flightfinder.data.repository.local.FlightsPreferencesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FlightFinderViewModel(
	private val flightsRepository: FlightsRepository,
	private val flightsPreferencesRepository: FlightsPreferencesRepository
) : ViewModel() {
	
	private val _flightFinderUiState = MutableStateFlow(FlightFinderUiState())
	val flightFinderUiState: StateFlow<FlightFinderUiState> = _flightFinderUiState.asStateFlow()
	
	
	init {
		viewModelScope.launch {
			val search = flightsPreferencesRepository.searchTerm.first()
			val favorites = flightsRepository.getAllFavorites().first()
			val allAirports = flightsRepository.getAllAirports().first()
			val suggestedAirports = if (search.isNotEmpty())
				flightsRepository.searchAirports("%$search%").first()
			else emptyList()
			_flightFinderUiState.update {
				it.copy(
					searchTerm = search,
					suggestedAirports = suggestedAirports,
					travels = emptyList(),
					favorites = favorites,
					allAirports = allAirports,
					favoritesKeys =
					favorites.map { "${it.departureCode}-${it.destinationCode}"}.toSet()
					)
			}
		}
	}
	
	fun onSearchTermChanged(term: String) {
		_flightFinderUiState.update { it.copy(searchTerm = term, travels = emptyList()) }
		viewModelScope.launch {
			flightsPreferencesRepository.saveSearchTerm(term)
		}
		onSearch(term)
	}
	
	private fun onSearch(term: String) {
			viewModelScope.launch {
				val suggestedAirports = if (term.isNotEmpty())
					flightsRepository.searchAirports("%$term%")
						.first() else emptyList()
				_flightFinderUiState.update { it.copy(suggestedAirports = suggestedAirports) }
			}
	}
	
	fun onAirportSelected(airport: Airport) {
		viewModelScope.launch {
			val travels = flightsRepository.getTravels(airport.airportCode)
				.first()
			_flightFinderUiState.update {
				it.copy(
					travels = travels,
					departAirport = airport,
				suggestedAirports = emptyList()
				)
			}
		}
	}
	
	private fun addToFavorites(favorite: Favorite) {
		viewModelScope.launch {
			flightsRepository.insertFavorite(favorite)
		}
	}
	
	private fun removeFromFavorites(favorite: Favorite) {
		viewModelScope.launch {
			flightsRepository.deleteFavorite(favorite)
		}
	}
	
	fun onFavoriteClicked(favorite: Favorite) {
		viewModelScope.launch {
			val currentFavorite = flightsRepository.getFavorite(favorite).first()
			if (currentFavorite != null) {
				removeFromFavorites(currentFavorite)
			}
			else {
				addToFavorites(favorite)
			}
			val favorites = flightsRepository.getAllFavorites().first()
			_flightFinderUiState.update {
				it.copy(
					favorites = favorites,
					favoritesKeys =
					favorites.map { "${it.departureCode}-${it.destinationCode}"}.toSet()
				)
			}
		}
	}
	
	companion object {
		val factory: ViewModelProvider.Factory = viewModelFactory {
			initializer {
				val app = (this[APPLICATION_KEY] as Application)
				FlightFinderViewModel(
					flightsRepository = app.container.flightsRepository,
					flightsPreferencesRepository = app.container.flightsPreferencesRepository
				)
			}
		}
	}
}

data class FlightFinderUiState(
	val searchTerm: String = "",
	val suggestedAirports: List<Airport> = emptyList(),
	val travels: List<Airport> = emptyList(),
	val departAirport: Airport = Airport(0,"","", 0),
	val favorites: List<Favorite> = emptyList(),
	val favoritesKeys: Set<String> = emptySet(),
	val allAirports: List<Airport> = emptyList()
)