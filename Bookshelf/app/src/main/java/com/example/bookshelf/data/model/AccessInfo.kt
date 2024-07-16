package com.example.bookshelf.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AccessInfo(
    @SerialName("country")
    val country: String = "",
    @SerialName("viewability")
    val viewability: String = "",
    @SerialName("embeddable")
    val embeddable: Boolean = false,
    @SerialName("publicDomain")
    val publicDomain: Boolean = false,
    @SerialName("textToSpeechPermission")
    val textToSpeechPermission: String = "",
    @SerialName("epub")
    val epub: Epub = Epub(),
    @SerialName("pdf")
    val pdf: Pdf = Pdf(),
    @SerialName("webReaderLink")
    val webReaderLink: String = "",
    @SerialName("accessViewStatus")
    val accessViewStatus: String = "",
    @SerialName("quoteSharingAllowed")
    val quoteSharingAllowed: Boolean = false
)