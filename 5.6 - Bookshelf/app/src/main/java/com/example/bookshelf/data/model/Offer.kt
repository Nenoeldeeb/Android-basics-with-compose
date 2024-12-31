package com.example.bookshelf.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Offer(
    @SerialName("finskyOfferType")
    val finskyOfferType: Int = 0,
    @SerialName("listPrice")
    val listPrice: ListPriceX = ListPriceX(),
    @SerialName("retailPrice")
    val retailPrice: RetailPriceX = RetailPriceX()
)