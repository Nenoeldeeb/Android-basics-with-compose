package com.example.marsphotos.fake

import com.example.marsphotos.data.MarsPhotosRepository

class FakeNetworkMarsPhotosRepository: MarsPhotosRepository {
	override suspend fun getPhotos() = FakeDataSource.photos
}