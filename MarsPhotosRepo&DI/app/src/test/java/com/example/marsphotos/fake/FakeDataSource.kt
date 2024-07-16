package com.example.marsphotos.fake

import com.example.marsphotos.model.MarsPhoto

object FakeDataSource {
	val photos = List(5) {
		MarsPhoto(
			id = "${it + 1}",
			imgSrc = "${it + 1}_url"
		)
	}
}