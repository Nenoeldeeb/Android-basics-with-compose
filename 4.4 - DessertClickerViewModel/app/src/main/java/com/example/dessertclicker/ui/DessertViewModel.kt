package com.example.dessertclicker.ui

import androidx.lifecycle.ViewModel
import com.example.dessertclicker.data.Datasource
import com.example.dessertclicker.model.Dessert
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DessertViewModel : ViewModel() {
	
	private val _uiState = MutableStateFlow(DessertUiState())
	val uiState = _uiState.asStateFlow()
	
	private val desserts = Datasource.dessertList
	
	init {
		updateDessertUiState(desserts.first())
	}
	
	private fun determineDessertToShow(): Dessert {
		var dessertToShow = desserts.first()
		for (dessert in desserts) {
			if (_uiState.value.dessertsSold >= dessert.startProductionAmount) dessertToShow = dessert
			else break
		}
		return dessertToShow
	}
	
	fun onDessertClick() {
		updateDessertUiState(determineDessertToShow())
	}
	
	private fun updateDessertUiState(dessert: Dessert) {
		_uiState.update {
			it.copy(
				dessertsSold = it.dessertsSold + 1,
				revenue = it.revenue + it.currentDessertPrice,
				currentDessertPrice = dessert.price,
				currentDessertImageId = dessert.imageId,
			)
		}
	}
}