package com.example.bookshelf.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReadingModes(
    @SerialName("text")
    val text: Boolean = false,
    @SerialName("image")
    val image: Boolean = false
)