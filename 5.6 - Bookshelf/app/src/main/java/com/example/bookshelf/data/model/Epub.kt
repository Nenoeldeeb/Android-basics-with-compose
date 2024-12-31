package com.example.bookshelf.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Epub(
    @SerialName("isAvailable")
    val isAvailable: Boolean = false,
    @SerialName("acsTokenLink")
    val acsTokenLink: String = "",
    @SerialName("downloadLink")
    val downloadLink: String = ""
)