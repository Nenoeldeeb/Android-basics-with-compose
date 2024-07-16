package com.example.cairocity.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.cairocity.R
import com.example.cairocity.model.Recommendation
import com.example.cairocity.ui.theme.CairoCityTheme
import com.example.cairocity.ui.theme.spacing
import com.example.cairocity.utils.RecommendationCategory

@Composable
internal fun RecommendationDetails(
	recommendation: Recommendation,
	onBackClicked: () -> Unit,
	contentPadding: PaddingValues,
	modifier: Modifier = Modifier,
) {
	BackHandler {
		onBackClicked()
	}
	val layoutDirection = LocalLayoutDirection.current
	Column(
		horizontalAlignment = Alignment.CenterHorizontally,
		modifier = modifier
			.verticalScroll(rememberScrollState())
			.padding(
				top = contentPadding.calculateTopPadding(),
				bottom = contentPadding.calculateBottomPadding(),
				start = contentPadding.calculateStartPadding(layoutDirection),
				end = contentPadding.calculateEndPadding(layoutDirection)
			)
	) {
		Text(
			text = stringResource(id = recommendation.nameResourceId),
			style = MaterialTheme.typography.titleMedium,
			modifier = Modifier
				.padding(
					horizontal = MaterialTheme.spacing.small,
					vertical = MaterialTheme.spacing.medium
				)
		)
		RecommendationItemImage(
			recommendationImageResourceId = recommendation.imageResourceId,
			contentDescription = recommendation.nameResourceId,
			modifier = Modifier
				.padding(
					vertical = MaterialTheme.spacing.medium,
					horizontal = MaterialTheme.spacing.small
				)
		)
		Text(
			text = stringResource(id = recommendation.descriptionResourceId),
			style = MaterialTheme.typography.bodyLarge,
			modifier = Modifier
				.padding(
					horizontal = MaterialTheme.spacing.small,
					vertical = MaterialTheme.spacing.medium
				)
		)
	}
}

@Preview(showBackground = true)
@Composable
private fun RecommendationDetailsPreview() {
	CairoCityTheme {
		RecommendationDetails(
			recommendation = Recommendation(
				nameResourceId = R.string.park_name_1,
				imageResourceId = R.drawable.park_1,
				descriptionResourceId = R.string.park_description_1,
				thumbnailResourceId = R.drawable.park_1_thumb,
				addressResourceId = R.string.park_address_1,
				category = RecommendationCategory.Parks
			),
			onBackClicked = {},
			contentPadding = PaddingValues(),
			modifier = Modifier
				.background(MaterialTheme.colorScheme.background)
		)
	}
}