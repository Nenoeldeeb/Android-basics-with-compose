package com.example.bookshelf.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VolumeInfo(
    @SerialName("title")
    val title: String = "",
    @SerialName("authors")
    val authors: List<String> = listOf(),
    @SerialName("publisher")
    val publisher: String = "",
    @SerialName("publishedDate")
    val publishedDate: String = "",
    @SerialName("description")
    val description: String = "",
    @SerialName("industryIdentifiers")
    val industryIdentifiers: List<IndustryIdentifier> = listOf(),
    @SerialName("readingModes")
    val readingModes: ReadingModes = ReadingModes(),
    @SerialName("pageCount")
    val pageCount: Int = 0,
    @SerialName("printType")
    val printType: String = "",
    @SerialName("categories")
    val categories: List<String> = listOf(),
    @SerialName("maturityRating")
    val maturityRating: String = "",
    @SerialName("allowAnonLogging")
    val allowAnonLogging: Boolean = false,
    @SerialName("contentVersion")
    val contentVersion: String = "",
    @SerialName("panelizationSummary")
    val panelizationSummary: PanelizationSummary = PanelizationSummary(),
    @SerialName("imageLinks")
    val imageLinks: ImageLinks = ImageLinks(),
    @SerialName("language")
    val language: String = "",
    @SerialName("previewLink")
    val previewLink: String = "",
    @SerialName("infoLink")
    val infoLink: String = "",
    @SerialName("canonicalVolumeLink")
    val canonicalVolumeLink: String = "",
    @SerialName("subtitle")
    val subtitle: String = "",
    @SerialName("averageRating")
    val averageRating: Int = 0,
    @SerialName("ratingsCount")
    val ratingsCount: Int = 0
)