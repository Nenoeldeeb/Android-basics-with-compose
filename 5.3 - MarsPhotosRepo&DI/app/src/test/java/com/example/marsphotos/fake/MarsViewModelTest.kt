package com.example.marsphotos.fake

import com.example.marsphotos.rules.TestDispatcherRule
import com.example.marsphotos.ui.screens.MarsUiState
import com.example.marsphotos.ui.screens.MarsViewModel
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class MarsViewModelTest {
	
	@get:Rule
	val testDispatcher = TestDispatcherRule()
	
	@Test
	fun marsViewModel_getMarsPhotos_verifyMarsUiStateSuccess() = runTest {
		// Given
		val marsRepository = FakeNetworkMarsPhotosRepository()
		val marsViewModel = MarsViewModel(marsRepository)
		
		// Then
		val marsUiState = marsViewModel.marsUiState
		assertEquals(
			marsUiState,
					MarsUiState.Success("Success: ${FakeDataSource.photos.size} Mars photos retrieved")
		)
	}
}