package com.example.monthofcooking.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Recipe (
	@StringRes val nameResourceId: Int,
	@StringRes val descriptionResourceId: Int,
	@DrawableRes val imageResourceId: Int
)