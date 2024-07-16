package com.example.busschedule.data

import kotlinx.coroutines.flow.Flow

interface BusScheduleRepository {
	fun getAllBusSchedules(): Flow<List<BusSchedule>>
	
	fun getBusSchedule(stopName: String): Flow<List<BusSchedule>>
}