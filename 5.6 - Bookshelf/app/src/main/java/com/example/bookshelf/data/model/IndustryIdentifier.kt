package com.example.bookshelf.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class IndustryIdentifier(
    @SerialName("type")
    val type: String = "",
    @SerialName("identifier")
    val identifier: String = ""
)