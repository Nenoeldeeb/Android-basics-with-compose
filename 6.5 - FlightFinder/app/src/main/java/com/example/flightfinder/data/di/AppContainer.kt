package com.example.flightfinder.data.di

import com.example.flightfinder.data.repository.FlightsRepository
import com.example.flightfinder.data.repository.local.FlightsPreferencesRepository

interface AppContainer {
	val flightsRepository: FlightsRepository
	
	val flightsPreferencesRepository: FlightsPreferencesRepository
}