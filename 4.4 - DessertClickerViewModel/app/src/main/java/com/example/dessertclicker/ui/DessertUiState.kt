package com.example.dessertclicker.ui

import androidx.annotation.DrawableRes

data class DessertUiState(
	val dessertsSold: Int = -1,
	val revenue: Int = 0,
	val currentDessertPrice: Int = 0,
	@DrawableRes val currentDessertImageId: Int = 0,
)
