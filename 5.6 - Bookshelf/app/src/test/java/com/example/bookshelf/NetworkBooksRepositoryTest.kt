package com.example.bookshelf

import com.example.bookshelf.data.repos.books.NetworkBooksRepository
import com.example.bookshelf.fake.FakeApiService
import com.example.bookshelf.fake.FakeDataSource
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class NetworkBooksRepositoryTest {
	
	@Test
	fun networkBooksRepository_searchBooks_returnsNumberOfBooks() = runTest {
		val repository = NetworkBooksRepository(FakeApiService())
		val books = repository.getBooks()
		assertEquals(books, FakeDataSource.booksResponse.books)
	}
}