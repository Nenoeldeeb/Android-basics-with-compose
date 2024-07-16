package com.example.busschedule.data

import android.content.Context

interface AppContainer {
	val busScheduleRepository: BusScheduleRepository
}

class DefaultAppContainer(
	private val context: Context
): AppContainer {
	override val busScheduleRepository: BusScheduleRepository by lazy {
		OfflineBusScheduleRepository(
			busScheduleDao = BusScheduleDatabase.getInstance(context).busScheduleDao()
		)
	}
}