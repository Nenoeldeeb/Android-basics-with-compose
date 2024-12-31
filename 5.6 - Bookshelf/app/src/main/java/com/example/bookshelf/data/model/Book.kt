package com.example.bookshelf.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Book(
    @SerialName("kind")
    val kind: String = "",
    @SerialName("id")
    val id: String = "",
    @SerialName("etag")
    val etag: String = "",
    @SerialName("selfLink")
    val selfLink: String = "",
    @SerialName("volumeInfo")
    val volumeInfo: VolumeInfo = VolumeInfo(),
    @SerialName("saleInfo")
    val saleInfo: SaleInfo = SaleInfo(),
    @SerialName("accessInfo")
    val accessInfo: AccessInfo = AccessInfo(),
    @SerialName("searchInfo")
    val searchInfo: SearchInfo = SearchInfo()
)