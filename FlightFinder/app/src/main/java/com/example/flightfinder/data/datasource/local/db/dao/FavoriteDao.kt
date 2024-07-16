package com.example.flightfinder.data.datasource.local.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.flightfinder.data.datasource.local.db.entity.Favorite
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {
	@Query("SELECT * FROM favorite")
	fun getAllFavorites(): Flow<List<Favorite>>
	
	@Query("SELECT * FROM favorite WHERE" +
			       " departure_code = :depCode AND destination_code = :destCode")
	fun getFavorite(depCode: String, destCode: String): Flow<Favorite>
	
	@Insert
	suspend fun insertFavorite(favorite: Favorite)
	
	@Delete
	suspend fun deleteFavorite(favorite: Favorite)
}