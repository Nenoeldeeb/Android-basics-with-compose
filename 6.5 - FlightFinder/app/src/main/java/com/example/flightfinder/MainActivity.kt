package com.example.flightfinder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.flightfinder.ui.screens.FlightsApp
import com.example.flightfinder.ui.theme.FlightFinderTheme

class MainActivity : ComponentActivity() {
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContent {
			FlightFinderTheme {
				FlightsApp()
			}
		}
	}
}
