package com.example.flightfinder.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.flightfinder.R
import com.example.flightfinder.data.datasource.local.db.entity.Airport
import com.example.flightfinder.data.datasource.local.db.entity.Favorite


@Composable
fun FlightsApp(
	modifier: Modifier = Modifier,
	viewModel: FlightFinderViewModel = viewModel(factory = FlightFinderViewModel.factory),
) {
	val uiState by viewModel.flightFinderUiState.collectAsState()
	Scaffold(
		topBar = {
			AppBar(
				searchTerm = uiState.searchTerm,
				onSearchTermChanged = viewModel::onSearchTermChanged,
				modifier = Modifier.fillMaxWidth()
			)
		}
	) { padding ->
		FlightFinderScreen(
			suggestedAirports = uiState.suggestedAirports,
			departAirport = uiState.departAirport,
			travels = uiState.travels,
			isDisplayingTravels = uiState.travels.isNotEmpty(),
			favorites = uiState.favorites,
			allAirports = uiState.allAirports,
			favoritesKeys = uiState.favoritesKeys,
			onAirportSelected = viewModel::onAirportSelected,
			onFavoriteClicked = viewModel::onFavoriteClicked,
			modifier = modifier,
			contentPadding = padding
		)
	}
}

@Composable
private fun AppBar(
	searchTerm: String,
	onSearchTermChanged: (String) -> Unit,
	modifier: Modifier = Modifier,
) {
	Row(modifier = modifier
		.fillMaxWidth()
		.padding(top = 32.dp)) {
		TextField(
			value = searchTerm,
			onValueChange = onSearchTermChanged,
			label = { Text(text = stringResource(id = R.string.airports_search)) },
			trailingIcon = {
				Icon(
					imageVector = Icons.Default.Search,
					contentDescription = stringResource(id = R.string.search_icon)
				)
			},
			keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
			modifier = Modifier.fillMaxWidth()
		)
	}
}

@Composable
private fun FlightFinderScreen(
	suggestedAirports: List<Airport>,
	departAirport: Airport,
	travels: List<Airport>,
	isDisplayingTravels: Boolean,
	favorites: List<Favorite>,
	allAirports: List<Airport>,
	favoritesKeys: Set<String>,
	onAirportSelected: (Airport) -> Unit,
	onFavoriteClicked: (Favorite) -> Unit,
	modifier: Modifier = Modifier,
	contentPadding: PaddingValues = PaddingValues(),
) {
	Box(
		contentAlignment = Alignment.TopCenter,
		modifier = modifier
			.fillMaxSize()
			.padding(
				top = contentPadding.calculateTopPadding(),
				bottom = contentPadding.calculateBottomPadding(),
				start = 16.dp,
				end = 16.dp
			)
	) {
		if (!isDisplayingTravels) {
			if (suggestedAirports.isNotEmpty()) {
				SuggestionsList(
					suggestedAirports = suggestedAirports,
					onAirportSelected = onAirportSelected,
				)
			}
			else {
				FavoritesList(
					favorites = favorites,
					allAirports = allAirports,
					onFavoriteClicked = onFavoriteClicked
				)
			}
		}
		else {
			TravelsList(
				departAirport = departAirport,
				travels = travels,
				favoritesKeys = favoritesKeys,
				onFavoriteClicked = onFavoriteClicked,
			)
		}
	}
}

@Composable
private fun SuggestionsList(
	suggestedAirports: List<Airport>,
	onAirportSelected: (Airport) -> Unit,
	modifier: Modifier = Modifier,
) {
	LazyColumn(modifier = modifier) {
		items(suggestedAirports, key = { it.id }) { airport ->
			AirportItem(
				airport = airport,
				onAirportSelected = onAirportSelected,
				modifier = Modifier
					.fillMaxWidth()
					.padding(8.dp)
			)
		}
	}
}

@Composable
private fun AirportItem(
	airport: Airport,
	onAirportSelected: (Airport) -> Unit,
	modifier: Modifier = Modifier,
) {
	Row(modifier = modifier.clickable { onAirportSelected(airport) }) {
		Text(
			text = airport.airportCode,
			style = MaterialTheme.typography.titleMedium,
			modifier = Modifier.padding(end = 16.dp)
		)
		Text(text = airport.airportName, style = TextStyle(fontSize = 12.sp))
	}
}

@Composable
private fun TravelItem(
	departAirport: Airport,
	arriveAirport: Airport,
	icon: @Composable () -> Unit,
	onFavoriteClicked: (Favorite) -> Unit,
	modifier: Modifier = Modifier,
) {
	Row(modifier = modifier) {
		Column(modifier = Modifier.weight(1f)) {
			Text(
				text = stringResource(id = R.string.depart),
				style = MaterialTheme.typography.labelSmall,
				modifier = Modifier.padding(bottom = 8.dp)
			)
			AirportItem(airport = departAirport, onAirportSelected = {})
			Text(
				text = stringResource(id = R.string.arrive),
				style = MaterialTheme.typography.labelSmall,
				modifier = Modifier.padding(vertical = 8.dp)
			)
			AirportItem(airport = arriveAirport, onAirportSelected = {})
			
		}
		IconButton(onClick = {
			onFavoriteClicked(
				Favorite(
					departureCode = departAirport.airportCode,
					destinationCode = arriveAirport.airportCode
				)
			)
		},
		           content = icon
		)
	}
}

@Composable
private fun TravelsList(
	departAirport: Airport,
	travels: List<Airport>,
	favoritesKeys: Set<String>,
	onFavoriteClicked: (Favorite) -> Unit,
	modifier: Modifier = Modifier,
	contentPadding: PaddingValues = PaddingValues(),
) {
	LazyColumn(modifier = modifier, contentPadding = contentPadding) {
		item {
			Text(
				text = stringResource(id = R.string.results_title, departAirport.airportCode),
				style = MaterialTheme.typography.titleMedium,
				modifier = Modifier.padding(8.dp)
			)
		}
		items(travels, key = { it.id }) { travel ->
			val icon: @Composable () -> Unit =
				if (favoritesKeys.contains("${departAirport.airportCode}-${travel.airportCode}")) {
					{
						Icon(
							imageVector = Icons.Default.Star,
							contentDescription = stringResource(id = R.string.remove_from_favorite),
							tint = Color(color = 0xFFFFEB3B)
						)
					}
				}
				else {
					{
						Icon(
							imageVector = Icons.Default.Star,
							contentDescription = stringResource(id = R.string.add_to_favorite),
						)
					}
				}
			TravelItem(
				departAirport = departAirport,
				arriveAirport = travel,
				icon = { icon() },
				onFavoriteClicked = onFavoriteClicked,
				modifier = Modifier
					.fillMaxWidth()
					.padding(8.dp)
			)
		}
	}
}

@Composable
private fun FavoritesList(
	favorites: List<Favorite>,
	allAirports: List<Airport>,
	onFavoriteClicked: (Favorite) -> Unit,
	modifier: Modifier = Modifier,
	contentPadding: PaddingValues = PaddingValues(),
) {
	LazyColumn(modifier = modifier, contentPadding = contentPadding) {
		item {
			Text(
				text = stringResource(id = R.string.favorites_title),
				style = MaterialTheme.typography.titleMedium,
				modifier = Modifier.padding(8.dp)
			)
		}
		if (favorites.isNotEmpty()) {
			items(favorites, key = { it.id }) { favorite ->
				val icon: @Composable () -> Unit = {
					Icon(
						imageVector = Icons.Default.Star,
						contentDescription = stringResource(id = R.string.remove_from_favorite),
						tint = Color(color = 0xFFFFEB3B)
					)
				}
				TravelItem(
					departAirport = allAirports.first { it.airportCode == favorite.departureCode },
					arriveAirport = allAirports.first { it.airportCode == favorite.destinationCode },
					icon = { icon() },
					onFavoriteClicked = onFavoriteClicked,
					modifier = Modifier
						.fillMaxWidth()
						.padding(8.dp)
				)
			}
		}
		else {
			item {
				Text(
					text = stringResource(id = R.string.no_favorites),
					style = MaterialTheme.typography.bodyMedium,
					modifier = Modifier.padding(8.dp)
				)
			}
		}
	}
}