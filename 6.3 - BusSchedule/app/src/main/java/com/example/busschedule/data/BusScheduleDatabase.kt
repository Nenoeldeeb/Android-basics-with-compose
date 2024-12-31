package com.example.busschedule.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [BusSchedule::class], version = 1, exportSchema = false)
abstract class BusScheduleDatabase: RoomDatabase() {
	abstract fun busScheduleDao(): BusScheduleDao
	
	companion object {
		@Volatile
		private var instance: BusScheduleDatabase? = null
		
		fun getInstance(context: Context): BusScheduleDatabase =
			instance ?: synchronized(this) {
				Room.databaseBuilder(
					context = context,
					klass = BusScheduleDatabase::class.java,
					name = "app_database"
				)
					.createFromAsset("database/bus_schedule.db")
					.fallbackToDestructiveMigration()
					.build().also { instance = it }
			}
	}
}