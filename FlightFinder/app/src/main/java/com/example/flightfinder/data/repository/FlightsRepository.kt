package com.example.flightfinder.data.repository

import com.example.flightfinder.data.datasource.local.db.entity.Airport
import com.example.flightfinder.data.datasource.local.db.entity.Favorite
import kotlinx.coroutines.flow.Flow

interface FlightsRepository {
	fun getAllFavorites(): Flow<List<Favorite>>
	
	fun getFavorite(favorite: Favorite): Flow<Favorite>
	
	suspend fun insertFavorite(favorite: Favorite)
	
	suspend fun deleteFavorite(favorite: Favorite)
	
	fun searchAirports(searchTerm: String): Flow<List<Airport>>
	
	fun getTravels(code: String): Flow<List<Airport>>
	
	fun getAllAirports(): Flow<List<Airport>>
}