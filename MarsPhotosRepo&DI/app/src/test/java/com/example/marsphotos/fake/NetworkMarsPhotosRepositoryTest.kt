package com.example.marsphotos.fake

import com.example.marsphotos.data.NetworkMarsPhotosRepository
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class NetworkMarsPhotosRepositoryTest {
	@Test
	fun networkMarsPhotosRepository_getPhotos_verifyPhotos() = runTest {
		// Create an instance of the NetworkMarsPhotosRepository
		// with the FakeMarsApiService
		val repository = NetworkMarsPhotosRepository(FakeMarsApiService())
		// Call the getMarsPhotos method
		val photos = repository.getPhotos()
		// Verify that the photos returned are the same as the
		// photos in the FakeDataSource
		assertEquals(FakeDataSource.photos, photos)
	}
}