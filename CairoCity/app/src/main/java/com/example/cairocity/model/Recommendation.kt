package com.example.cairocity.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.cairocity.utils.RecommendationCategory

data class Recommendation(
	val category: RecommendationCategory,
	@StringRes val nameResourceId: Int,
	@StringRes val addressResourceId: Int,
	@StringRes val descriptionResourceId: Int,
	@DrawableRes val imageResourceId: Int,
	@DrawableRes val thumbnailResourceId: Int
)
