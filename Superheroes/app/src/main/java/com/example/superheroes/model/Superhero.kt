package com.example.superheroes.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Superhero (
	@StringRes val nameResourceId: Int,
	@StringRes val descriptionResourceId: Int,
	@DrawableRes val imageResourceId: Int
)