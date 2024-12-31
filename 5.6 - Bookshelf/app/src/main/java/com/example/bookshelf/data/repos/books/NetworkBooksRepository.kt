package com.example.bookshelf.data.repos.books

import com.example.bookshelf.data.model.Book
import com.example.bookshelf.data.sources.network.BooksApiService

class NetworkBooksRepository(
	private val booksApiService: BooksApiService
): BooksRepository {
	
	override suspend fun getBooks(): List<Book> =
		booksApiService.searchBooks().books
}