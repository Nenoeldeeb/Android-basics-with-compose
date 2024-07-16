package com.example.bookshelf.data.di

import com.example.bookshelf.data.repos.books.NetworkBooksRepository
import com.example.bookshelf.data.sources.network.BooksApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

class DefaultAppContainer: AppContainer{
	private val baseUrl =
		"https://www.googleapis.com/books/v1/"
	
	private val retrofit = Retrofit.Builder()
		.baseUrl(baseUrl)
		.addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
		.build()
	
	private val bookService = retrofit.create(BooksApiService::class.java)
	
	override val booksRepository by lazy {
		NetworkBooksRepository(bookService)
	}
}