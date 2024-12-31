package com.example.bookshelf.data.sources.network

import com.example.bookshelf.data.model.BooksResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BooksApiService {
	
	@GET("volumes?q=android+development")
	suspend fun searchBooks(): BooksResponse
}