package com.example.bookshelf.data.di

import com.example.bookshelf.data.repos.books.BooksRepository

interface AppContainer {
	val booksRepository: BooksRepository
}