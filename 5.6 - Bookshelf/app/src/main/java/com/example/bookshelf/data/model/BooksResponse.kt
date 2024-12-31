package com.example.bookshelf.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BooksResponse(
    @SerialName("kind")
    val kind: String = "",
    @SerialName("totalItems")
    val totalItems: Int = 0,
    @SerialName("items")
    val books: List<Book> = listOf()
)