package com.example.flightfinder.data.datasource.local.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.flightfinder.data.datasource.local.db.entity.Airport
import kotlinx.coroutines.flow.Flow

@Dao
interface AirportDao {
	@Query(
		"SELECT * FROM airport WHERE" +
				" iata_code LIKE :searchQuery OR name LIKE :searchQuery ORDER BY passengers"
	)
	fun searchAirports(searchQuery: String): Flow<List<Airport>>
	
	@Query("SELECT * FROM airport WHERE iata_code != :code")
	fun getTravels(code: String): Flow<List<Airport>>
	
	@Query("SELECT * FROM airport")
	fun getAllAirports(): Flow<List<Airport>>
}