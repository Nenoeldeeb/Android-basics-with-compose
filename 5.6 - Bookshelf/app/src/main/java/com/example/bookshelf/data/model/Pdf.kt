package com.example.bookshelf.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Pdf(
    @SerialName("isAvailable")
    val isAvailable: Boolean = false,
    @SerialName("acsTokenLink")
    val acsTokenLink: String = ""
)