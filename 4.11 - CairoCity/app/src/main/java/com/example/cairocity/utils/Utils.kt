package com.example.cairocity.utils

import androidx.annotation.StringRes
import com.example.cairocity.R

enum class RecommendationCategory(@StringRes val titleResourceId: Int) {
	StartScreen(titleResourceId = R.string.app_name),
	CoffeeShops(titleResourceId = R.string.category_coffee_shops),
	Restaurants(titleResourceId = R.string.category_restaurants),
	KidsAreas(titleResourceId = R.string.category_kids_areas),
	Parks(titleResourceId = R.string.category_parks),
	ShoppingCenters(titleResourceId = R.string.category_shopping_centers),
	DetailsScreen(titleResourceId = R.string.app_name)
}

enum class RecommendationContentType {
	LISTONLY, LISTANDDETAILS
}
