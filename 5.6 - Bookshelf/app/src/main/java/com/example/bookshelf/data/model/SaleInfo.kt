package com.example.bookshelf.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SaleInfo(
    @SerialName("country")
    val country: String = "",
    @SerialName("saleability")
    val saleability: String = "",
    @SerialName("isEbook")
    val isEbook: Boolean = false,
    @SerialName("listPrice")
    val listPrice: ListPrice = ListPrice(),
    @SerialName("retailPrice")
    val retailPrice: RetailPrice = RetailPrice(),
    @SerialName("buyLink")
    val buyLink: String = "",
    @SerialName("offers")
    val offers: List<Offer> = listOf()
)