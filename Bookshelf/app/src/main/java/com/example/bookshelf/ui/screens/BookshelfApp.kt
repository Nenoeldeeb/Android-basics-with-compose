package com.example.bookshelf.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookshelf.R
import com.example.bookshelf.ui.BookshelfViewModel

@Composable
fun BookshelfApp() {
	val viewModel: BookshelfViewModel = viewModel(
		factory = BookshelfViewModel.factory,
	)
	
	Scaffold(
		topBar = { BookshelfAppBar() }
	) {padding ->
			BookshelfBooksScreen(
				uiState = viewModel.uiState,
				onRetryClick = viewModel::getBooks,
				contentPadding = padding
			)
	}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BookshelfAppBar(
	modifier: Modifier = Modifier,
) {
	MediumTopAppBar(
		title = { Text(text = stringResource(id = R.string.app_name)) },
		modifier = modifier
	)
}