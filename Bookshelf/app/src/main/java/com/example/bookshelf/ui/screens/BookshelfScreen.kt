package com.example.bookshelf.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.bookshelf.R
import com.example.bookshelf.data.model.Book
import com.example.bookshelf.ui.BookshelfUiState
import com.example.bookshelf.ui.theme.spacing


@Composable
fun BookshelfBooksScreen(
	uiState: BookshelfUiState,
	onRetryClick: () -> Unit,
	modifier: Modifier = Modifier,
	contentPadding: PaddingValues = PaddingValues(0.dp),
) {
	when (uiState) {
		is BookshelfUiState.Loading -> BookshelfLoadingScreen(
			modifier = modifier.fillMaxSize(),
		)
		is BookshelfUiState.Error -> BookshelfErrorScreen(
			errorMessage = uiState.errorMessage,
			onRetryClick = onRetryClick,
			modifier = modifier.fillMaxSize(),
		)
		is BookshelfUiState.Success -> BookshelfResultsScreen(
			books = uiState.books,
			modifier = modifier.fillMaxSize(),
			contentPadding = contentPadding
		)
	}
}

@Composable
private fun BookshelfResultsScreen(
	books: List<Book>,
	modifier: Modifier = Modifier,
	contentPadding: PaddingValues = PaddingValues(0.dp),
) {
	BookshelfBooksGrid(
		books = books,
		contentPadding = contentPadding,
		modifier = modifier.padding(
				horizontal = MaterialTheme.spacing.small,
				vertical = MaterialTheme.spacing.medium
			
			)
	)
}

@Composable
private fun BookshelfLoadingScreen(
	modifier: Modifier = Modifier,
) {
	Column(
		verticalArrangement = Arrangement.Center,
		horizontalAlignment = Alignment.CenterHorizontally,
		modifier = modifier
	) {
		Image(
			painter = painterResource(id = R.drawable.loading_img),
			contentDescription = stringResource(id = R.string.loading),
			modifier = Modifier.size(200.dp)
		)
		Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
		Text(
			text = stringResource(id = R.string.loading),
			style = MaterialTheme.typography.bodyLarge,
		)
	}
}

@Composable
private fun BookshelfErrorScreen(
	errorMessage: String,
	onRetryClick: () -> Unit,
	modifier: Modifier = Modifier,
) {
	Column(
		verticalArrangement = Arrangement.Center,
		horizontalAlignment = Alignment.CenterHorizontally,
		modifier = modifier
	) {
		Image(
			painter = painterResource(id = R.drawable.connection_error),
			contentDescription = stringResource(id = R.string.connection_error),
		)
		Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
		Text(
			text = errorMessage,
			style = MaterialTheme.typography.bodyLarge,
		)
		Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
		Button(onClick = onRetryClick) {
			Text(text = stringResource(id = R.string.retry))
		}
	}
}

@Composable
private fun BookshelfBooksGrid(
	books: List<Book>,
	modifier: Modifier = Modifier,
	contentPadding: PaddingValues = PaddingValues(0.dp),
) {
	LazyVerticalGrid(
		columns = GridCells.Adaptive(MaterialTheme.spacing.imageSize),
		contentPadding = contentPadding,
		modifier = modifier,
	) {
		items(items = books, key = {it.id}) { book ->
			BookshelfGridItem(
				thumbUrl = book.volumeInfo.imageLinks.thumbnail,
				title = book.volumeInfo.title,
				modifier = Modifier
					.aspectRatio(2 / 3f)
					.padding(MaterialTheme.spacing.extraSmall),
			)
		}
	}
}

@Composable
private fun BookshelfGridItem(
	thumbUrl: String,
	title: String,
	modifier: Modifier = Modifier,
) {
	Card(
		modifier = modifier,
		elevation = CardDefaults.cardElevation(
			defaultElevation = MaterialTheme.spacing.extraSmall
		),
	) {
		AsyncImage(
			model = ImageRequest.Builder(LocalContext.current)
				.data(thumbUrl)
				.crossfade(true)
				.build(),
			contentDescription = stringResource(
				id = R.string.thumbnail_content_description, title
			),
			error = painterResource(id = R.drawable.broken_image),
			placeholder = painterResource(id = R.drawable.loading_img),
			contentScale = ContentScale.Crop,
			modifier = Modifier.fillMaxWidth(),
		)
	}
}