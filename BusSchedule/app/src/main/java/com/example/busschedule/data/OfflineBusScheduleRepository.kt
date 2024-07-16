package com.example.busschedule.data

import kotlinx.coroutines.flow.Flow

class OfflineBusScheduleRepository(
	private val busScheduleDao: BusScheduleDao
): BusScheduleRepository {
	
	override fun getAllBusSchedules(): Flow<List<BusSchedule>> =
		busScheduleDao.getAllSchedule()
	
	override fun getBusSchedule(stopName: String): Flow<List<BusSchedule>> =
		busScheduleDao.getSchedule(stopName)
}