package com.example.superheroes.model

import com.example.superheroes.R

object AllSuperheroes {
	val allHeroes = listOf(
		Superhero(
			nameResourceId = R.string.hero1,
			descriptionResourceId = R.string.description1,
			imageResourceId = R.drawable.android_superhero1
		),
		Superhero(
			nameResourceId = R.string.hero2,
			descriptionResourceId = R.string.description2,
			imageResourceId = R.drawable.android_superhero2
		),
		Superhero(
			nameResourceId = R.string.hero3,
			descriptionResourceId = R.string.description3,
			imageResourceId = R.drawable.android_superhero3
		),
		Superhero(
			nameResourceId = R.string.hero4,
			descriptionResourceId = R.string.description4,
			imageResourceId = R.drawable.android_superhero4
		),
		Superhero(
			nameResourceId = R.string.hero5,
			descriptionResourceId = R.string.description5,
			imageResourceId = R.drawable.android_superhero5
		),
		Superhero(
			nameResourceId = R.string.hero6,
			descriptionResourceId = R.string.description6,
			imageResourceId = R.drawable.android_superhero6
		)
	)
}