package com.example.amphibians.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.amphibians.AmphibiansApplication
import com.example.amphibians.data.AmphibianRepository
import com.example.amphibians.model.Amphibian
import kotlinx.coroutines.launch

class AmphibiansViewModel(
	private val amphibiansRepository: AmphibianRepository
): ViewModel() {
	var amphibiansUiState: AmphibiansUiState by
	mutableStateOf<AmphibiansUiState>(AmphibiansUiState.Loading)
	private set
	
	init {
		getAmphibians()
	}
	
	fun getAmphibians() {
		amphibiansUiState = AmphibiansUiState.Loading
		viewModelScope.launch {
			amphibiansUiState = try {
				AmphibiansUiState.Success(amphibiansRepository.getAmphibians())
			} catch (e: Exception) {
				AmphibiansUiState.Error(e.message ?: "An error occurred")
			}
		}
	}
	
	companion object {
		val factory = viewModelFactory {
			initializer {
				val app = (this[APPLICATION_KEY] as AmphibiansApplication)
				AmphibiansViewModel(app.appContainer.amphibianRepository)
			}
		}
	}
}

sealed class AmphibiansUiState {
	object Loading: AmphibiansUiState()
	data class Success(val amphibians: List<Amphibian>): AmphibiansUiState()
	data class Error(val message: String): AmphibiansUiState()
}