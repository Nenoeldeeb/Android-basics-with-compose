package com.example.amphibians.ui.screens

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.amphibians.R
import com.example.amphibians.ui.AmphibiansViewModel


@Composable
fun AmphibiansApp(
	windowSize: WindowSizeClass,
	modifier: Modifier = Modifier,
) {
	val viewMode: AmphibiansViewModel = viewModel(
		factory = AmphibiansViewModel.factory
	)
	
	Scaffold(
		topBar = { AmphibiansAppBar() },
		content = { padding ->
			AmphibiansHomeScreen(
				uiState = viewMode.amphibiansUiState,
				isMobile =
				windowSize.widthSizeClass == WindowWidthSizeClass.Compact,
				onRetryClick = { viewMode.getAmphibians() },
				contentPadding = padding,
			)
		},
		modifier = modifier
	)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AmphibiansAppBar(modifier: Modifier = Modifier) {
	MediumTopAppBar(
		title = { Text(text = stringResource(id = R.string.app_name)) },
		colors = TopAppBarDefaults.topAppBarColors(
			containerColor = MaterialTheme.colorScheme.primaryContainer,
			titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
		),
		modifier = modifier
	)
}