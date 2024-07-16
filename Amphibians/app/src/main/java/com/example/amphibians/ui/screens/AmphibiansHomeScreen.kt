package com.example.amphibians.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import com.example.amphibians.R
import com.example.amphibians.model.Amphibian
import com.example.amphibians.ui.AmphibiansUiState
import com.example.amphibians.ui.theme.spacing

@Composable
fun AmphibiansHomeScreen(
	isMobile: Boolean,
	uiState: AmphibiansUiState,
	onRetryClick: () -> Unit,
	modifier: Modifier = Modifier,
	contentPadding: PaddingValues = PaddingValues(0.dp),
) {
	when (uiState) {
		is AmphibiansUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
		is AmphibiansUiState.Error -> ErrorScreen(
			errorMessage = uiState.message,
			onRetryClick = onRetryClick,
			modifier = modifier.fillMaxSize()
		)
		is AmphibiansUiState.Success -> ResultsScreen(
			amphibians = uiState.amphibians,
			isMobile = isMobile,
			contentPadding = contentPadding,
			modifier = modifier.fillMaxSize()
		)
	}
}

@Composable
private fun ResultsScreen(
	amphibians: List<Amphibian>,
	isMobile: Boolean,
	contentPadding: PaddingValues,
	modifier: Modifier = Modifier,
) {
	AmphibiansList(
		amphibians = amphibians,
		isMobile = isMobile,
		contentPadding = contentPadding,
		modifier = modifier
	)
}

@Composable
private fun LoadingScreen(modifier: Modifier = Modifier) {
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
		Text(
			text = stringResource(id = R.string.loading),
			style = MaterialTheme.typography.bodyMedium,
			modifier = Modifier.align(Alignment.CenterHorizontally)
		)
	}
}

@Composable
private fun ErrorScreen(
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
			contentDescription = stringResource(id = R.string.no_internet),
		)
		Text(
			text = errorMessage,
			style = MaterialTheme.typography.bodyMedium,
		)
		 Button(onClick = onRetryClick) {
		 	Text(text = stringResource(id = R.string.retry))
		 }
	}
}

@Composable
private fun AmphibiansList(
	amphibians: List<Amphibian>,
	isMobile: Boolean,
	contentPadding: PaddingValues,
	modifier: Modifier = Modifier,
) {
	LazyColumn(
		modifier = modifier,
		contentPadding = contentPadding,
	) {
		items(items = amphibians, key = { it.id }) { amphibian ->
			AmphibiansListItem(
				amphibian = amphibian,
				isMobile = isMobile,
				modifier = Modifier.padding(
					horizontal = MaterialTheme.spacing.small,
					vertical = MaterialTheme.spacing.small
				)
			)
		}
	}
}

@Composable
private fun AmphibiansListItem(
	amphibian: Amphibian,
	isMobile: Boolean,
	modifier: Modifier = Modifier,
) {
	Card(
		elevation = CardDefaults.cardElevation(
			defaultElevation = MaterialTheme.spacing.extraSmall
		),
		shape = MaterialTheme.shapes.medium,
		modifier = modifier,
	) {
		when {
			isMobile -> AmphibianCardMobile(amphibian)
			else -> AmphibianCardTablet(amphibian)
		}
	}
}

@Composable
private fun AmphibianCardMobile(
	amphibian: Amphibian,
	modifier: Modifier = Modifier,
) {
	Column(modifier = modifier) {
		Text(
			text = "${amphibian.name} (${amphibian.type})",
			style = MaterialTheme.typography.titleSmall,
			modifier = Modifier.padding(MaterialTheme.spacing.small)
		)
		AmphibianCardImage(
			imageSrc = amphibian.imgSrc,
			contentDescription = amphibian.name,
			modifier = Modifier.fillMaxWidth()
		)
		Text(
			text = amphibian.description,
			style = MaterialTheme.typography.bodyMedium,
			modifier = Modifier.padding(MaterialTheme.spacing.small)
		)
	}
}

@Composable
private fun AmphibianCardTablet(
	amphibian: Amphibian,
	modifier: Modifier = Modifier,
) {
	Row(
		horizontalArrangement = Arrangement.Center,
		modifier = modifier
	) {
		AmphibianCardImage(
			imageSrc = amphibian.imgSrc,
			contentDescription = amphibian.name,
			modifier = Modifier.fillMaxWidth(0.5f)
		)
		Column(modifier = Modifier.padding(MaterialTheme.spacing.small)) {
			Text(
				text = "${amphibian.name} (${amphibian.type})",
				style = MaterialTheme.typography.titleSmall,
			)
			Text(
				text = amphibian.description,
				style = MaterialTheme.typography.bodyMedium,
			)
		}
	}
}

@Composable
private fun AmphibianCardImage(
	imageSrc: String,
	contentDescription: String,
	modifier: Modifier = Modifier,
) {
	Box(modifier = modifier) {
		AsyncImage(
			model = ImageRequest.Builder(LocalContext.current)
				.data(imageSrc)
				.crossfade(true)
				.build(),
			placeholder = painterResource(id = R.drawable.loading_img),
			error = painterResource(id = R.drawable.broken_image),
			contentDescription = "$contentDescription image",
			contentScale = ContentScale.Crop,
			modifier = Modifier.fillMaxWidth()
		)
	}
}