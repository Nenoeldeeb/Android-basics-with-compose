package com.example.flightfinder.data.repository.local

import com.example.flightfinder.data.datasource.local.db.dao.AirportDao
import com.example.flightfinder.data.datasource.local.db.dao.FavoriteDao
import com.example.flightfinder.data.datasource.local.db.entity.Airport
import com.example.flightfinder.data.datasource.local.db.entity.Favorite
import com.example.flightfinder.data.repository.FlightsRepository
import kotlinx.coroutines.flow.Flow

class OfflineFlightsRepository(
	private val favoriteDao: FavoriteDao,
	private val airportDao: AirportDao
): FlightsRepository {
	
	override fun getAllFavorites(): Flow<List<Favorite>> {
		return favoriteDao.getAllFavorites()
	}
	
	override fun getFavorite(favorite: Favorite): Flow<Favorite> {
		return favoriteDao.getFavorite(
			depCode = favorite.departureCode,
			destCode = favorite.destinationCode
		)
	}
	
	override suspend fun insertFavorite(favorite: Favorite) {
		favoriteDao.insertFavorite(favorite)
	}
	
	override suspend fun deleteFavorite(favorite: Favorite) {
		favoriteDao.deleteFavorite(favorite)
	}
	
	override fun searchAirports(searchTerm: String): Flow<List<Airport>> {
		return airportDao.searchAirports(searchTerm)
	}
	
	override fun getTravels(code: String): Flow<List<Airport>> {
		return airportDao.getTravels(code)
	}
	
	override fun getAllAirports(): Flow<List<Airport>> {
		return airportDao.getAllAirports()
	}
}