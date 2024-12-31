package com.example.bookshelf.data.repos.books

import com.example.bookshelf.data.model.Book

interface BooksRepository {
	suspend fun getBooks(): List<Book>
}