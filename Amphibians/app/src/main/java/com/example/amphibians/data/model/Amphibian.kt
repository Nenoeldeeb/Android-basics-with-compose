package com.example.amphibians.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class Amphibian(
	val id: Int = UUID.randomUUID().leastSignificantBits.toInt(),
	val name: String,
	val type: String,
	val description: String,
	@SerialName(value = "img_src") val imgSrc: String
)
