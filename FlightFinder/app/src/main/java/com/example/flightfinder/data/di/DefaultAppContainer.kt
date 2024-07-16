package com.example.flightfinder.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.flightfinder.data.datasource.local.db.FlightsDatabase
import com.example.flightfinder.data.repository.FlightsRepository
import com.example.flightfinder.data.repository.local.FlightsPreferencesRepository
import com.example.flightfinder.data.repository.local.OfflineFlightsRepository

class DefaultAppContainer(private val context: Context): AppContainer {
	
	override val flightsRepository: FlightsRepository by lazy {
		OfflineFlightsRepository(
			airportDao = FlightsDatabase.getInstance(context).airportDao(),
			favoriteDao = FlightsDatabase.getInstance(context).favoriteDao()
		)
	}
	
	
	override val flightsPreferencesRepository: FlightsPreferencesRepository by lazy {
		FlightsPreferencesRepository(dataStore = context.dataStore)
	}
}

private const val PREFERENCES_NAME = "flights_preferences"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
	name = PREFERENCES_NAME
)