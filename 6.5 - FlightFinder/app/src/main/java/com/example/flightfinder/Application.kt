package com.example.flightfinder

import android.app.Application
import com.example.flightfinder.data.di.AppContainer
import com.example.flightfinder.data.di.DefaultAppContainer

class Application: Application() {
	lateinit var container: AppContainer
	
	override fun onCreate() {
		super.onCreate()
		container = DefaultAppContainer(this)
	}
}