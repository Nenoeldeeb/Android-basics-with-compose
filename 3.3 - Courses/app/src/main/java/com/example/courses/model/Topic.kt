package com.example.courses.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic (
	@StringRes val topicNameID: Int,
	val associatedCourses: Int,
	@DrawableRes val topicImageID: Int
)