package com.example.amphibians.data

import com.example.amphibians.network.AmphibiansApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit


interface AppContainer {
	val amphibianRepository: AmphibianRepository
}

class DefaultAppContainer: AppContainer {

	private val baseUrl =
		"https://android-kotlin-fun-mars-server.appspot.com/"
	
	private val retrofit = Retrofit.Builder()
		.baseUrl(baseUrl)
		.addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
		.build()
	
	private val amphibianApi = retrofit.create(AmphibiansApiService::class.java)
	
	override val amphibianRepository: AmphibianRepository by lazy {
		NetworkAmphibianRepository(amphibianApi)
	}
}