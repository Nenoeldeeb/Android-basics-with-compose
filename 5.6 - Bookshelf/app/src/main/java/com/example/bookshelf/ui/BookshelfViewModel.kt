package com.example.bookshelf.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.bookshelf.BookshelfApplication
import com.example.bookshelf.data.model.Book
import com.example.bookshelf.data.repos.books.BooksRepository
import kotlinx.coroutines.launch

class BookshelfViewModel(
	private val repository: BooksRepository
) : ViewModel() {
	
	var uiState by mutableStateOf<BookshelfUiState>(BookshelfUiState.Loading)
		private set
	
	init {
		getBooks()
	}
	
	fun getBooks() {
		uiState = BookshelfUiState.Loading
		viewModelScope.launch {
			uiState = try {
				val books = repository.getBooks()
					.map { book ->
						book.copy(
							volumeInfo = book.volumeInfo.copy(
								imageLinks = book.volumeInfo.imageLinks.copy(
									thumbnail =
									book.volumeInfo.imageLinks.thumbnail.replace("http://", "https://")
								)
							)
						)
					}
				BookshelfUiState.Success(books)
			} catch (e: Exception) {
				BookshelfUiState.Error(e.message ?: "An error occurred")
			}
		}
	}
	
	companion object {
		val factory: ViewModelProvider.Factory = viewModelFactory {
			initializer {
				val app = (this[APPLICATION_KEY] as BookshelfApplication)
				BookshelfViewModel(app.container.booksRepository)
			}
		}
	}
}

sealed class BookshelfUiState {
	object Loading : BookshelfUiState()
	data class Error(val errorMessage: String) : BookshelfUiState()
	data class Success(val books: List<Book>) : BookshelfUiState()
}