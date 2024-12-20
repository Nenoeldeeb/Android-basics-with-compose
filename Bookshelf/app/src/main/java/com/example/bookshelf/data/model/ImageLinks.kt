package com.example.bookshelf.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ImageLinks(
    @SerialName("smallThumbnail")
    val smallThumbnail: String = "",
    @SerialName("thumbnail")
    val thumbnail: String = ""
)