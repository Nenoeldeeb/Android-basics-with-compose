package com.example.bookshelf.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ListPrice(
    @SerialName("amount")
    val amount: Double = 0.0,
    @SerialName("currencyCode")
    val currencyCode: String = ""
)