package com.example.bookshelf.fake

import com.example.bookshelf.data.model.BooksResponse
import com.example.bookshelf.data.sources.network.BooksApiService

class FakeApiService: BooksApiService {
	
	override suspend fun searchBooks(): BooksResponse =
		FakeDataSource.booksResponse
}