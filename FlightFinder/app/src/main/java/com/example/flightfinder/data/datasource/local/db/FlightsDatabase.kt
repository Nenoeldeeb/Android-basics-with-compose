package com.example.flightfinder.data.datasource.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.flightfinder.data.datasource.local.db.dao.AirportDao
import com.example.flightfinder.data.datasource.local.db.dao.FavoriteDao
import com.example.flightfinder.data.datasource.local.db.entity.Airport
import com.example.flightfinder.data.datasource.local.db.entity.Favorite

@Database(entities = [Airport::class, Favorite::class], version = 2)
abstract class FlightsDatabase: RoomDatabase() {
	abstract fun airportDao(): AirportDao
	abstract fun favoriteDao(): FavoriteDao
	
	companion object {
		@Volatile
		private var INSTANCE: FlightsDatabase? = null
		
		fun getInstance(context: Context): FlightsDatabase {
			return INSTANCE ?: synchronized(this) {
				Room.databaseBuilder(
					context = context,
					klass = FlightsDatabase::class.java,
					name = "flights_database"
				)
					.createFromAsset("database/flight_search.db")
					.build().also { INSTANCE = it }
			}
		}
	}
}