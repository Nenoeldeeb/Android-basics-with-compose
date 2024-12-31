package com.example.bookshelf.fake

import com.example.bookshelf.data.model.Book
import com.example.bookshelf.data.model.BooksResponse
import okhttp3.Response

object FakeDataSource {
	val booksResponse = BooksResponse(
		books = List(10) {Book()}
	)
}