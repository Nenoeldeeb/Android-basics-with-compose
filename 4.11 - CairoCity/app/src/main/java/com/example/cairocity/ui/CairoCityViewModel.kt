package com.example.cairocity.ui

import androidx.lifecycle.ViewModel
import com.example.cairocity.data.LocalRecommendationsProvider.getDefaultRecommendation
import com.example.cairocity.data.LocalRecommendationsProvider.getRecommendationsByCategory
import com.example.cairocity.model.Recommendation
import com.example.cairocity.utils.RecommendationCategory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CairoCityViewModel : ViewModel() {
	
	private val _uiState = MutableStateFlow(
		CairoCityUiState(
			currentRecommendations = getRecommendationsByCategory(
				RecommendationCategory.CoffeeShops
			),
			selectedRecommendation = getDefaultRecommendation(),
			currentCategory = RecommendationCategory.CoffeeShops,
		)
	)
	val uiState = _uiState.asStateFlow()
	
	fun onRecommendationSelected(recommendation: Recommendation) {
		_uiState.update {
			it.copy(
				selectedRecommendation = recommendation,
			)
		}
	}
	
	fun onCategorySelected(category: RecommendationCategory) {
		val recommendations = getRecommendationsByCategory(category)
		_uiState.update {
			it.copy(
				currentRecommendations = recommendations,
				selectedRecommendation = recommendations[0],
				currentCategory = category,
			)
		}
	}
}

data class CairoCityUiState(
	val currentRecommendations: List<Recommendation> = emptyList(),
	val selectedRecommendation: Recommendation,
	val currentCategory: RecommendationCategory,
)