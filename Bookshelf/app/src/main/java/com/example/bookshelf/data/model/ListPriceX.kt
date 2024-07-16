package com.example.bookshelf.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ListPriceX(
    @SerialName("amountInMicros")
    val amountInMicros: Int = 0,
    @SerialName("currencyCode")
    val currencyCode: String = ""
)