package com.example.flightfinder.data.repository.local

import android.nfc.Tag
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class FlightsPreferencesRepository(
	private val dataStore: DataStore<Preferences>
) {
	suspend fun saveSearchTerm(searchTerm: String) {
		dataStore.edit { preferences ->
			preferences[SEARCH_TERM] = searchTerm
		}
	}
	
	val searchTerm: Flow<String> = dataStore.data.catch {e ->
		if (e is IOException) {
			Log.e(Tag, "Error reading preferences", e)
			emit(emptyPreferences())
		} else {
			throw e
		}
	}.map { preferences ->
		preferences[SEARCH_TERM] ?: ""
	}
	
	private companion object {
		val SEARCH_TERM = stringPreferencesKey("search_term")
		const val Tag = "FlightsPreferencesRepository"
	}
}