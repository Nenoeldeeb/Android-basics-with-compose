package com.example.cairocity.ui.screens

import androidx.activity.compose.BackHandler
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.cairocity.R
import com.example.cairocity.model.Recommendation
import com.example.cairocity.ui.theme.CairoCityTheme
import com.example.cairocity.ui.theme.spacing
import com.example.cairocity.utils.RecommendationCategory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun RecommendationItem(
	recommendation: Recommendation,
	onRecommendationSelected: (Recommendation) -> Unit,
	isSelected: Boolean,
	modifier: Modifier = Modifier
) {
	Card(
		elevation = CardDefaults.cardElevation(
			defaultElevation = MaterialTheme.spacing.extraSmall
		),
		colors = CardDefaults.cardColors(
			containerColor = if (isSelected) MaterialTheme.colorScheme.primaryContainer
			else MaterialTheme.colorScheme.surface
		),
		shape = MaterialTheme.shapes.medium,
		modifier = modifier.height(MaterialTheme.spacing.imageSize),
		onClick = { onRecommendationSelected(recommendation) }
	) {
		Row {
			RecommendationItemImage(
				recommendationImageResourceId = recommendation.thumbnailResourceId,
				contentDescription = recommendation.nameResourceId,
			)
			RecommendationItemDetails(
				recommendationNameResourceId = recommendation.nameResourceId,
				recommendationAddressResourceId = recommendation.addressResourceId,
				modifier = Modifier
					.padding(
						vertical = MaterialTheme.spacing.small,
						horizontal = MaterialTheme.spacing.medium
					)
					.weight(1f)
			)
		}
	}
}

@Composable
internal fun RecommendationItemImage(
	@DrawableRes recommendationImageResourceId: Int,
	@StringRes contentDescription: Int,
	modifier: Modifier = Modifier
) {
	Box(
		modifier = modifier
	) {
		Image(
			painter = painterResource(id = recommendationImageResourceId),
			contentDescription = "${stringResource(id = contentDescription)} image",
			contentScale = ContentScale.Crop,
			modifier = Modifier.fillMaxHeight()
		)
	}
}

@Composable
private fun RecommendationItemDetails(
	@StringRes recommendationNameResourceId: Int,
	@StringRes recommendationAddressResourceId: Int,
	modifier: Modifier = Modifier
) {
	Column(
		modifier = modifier
	) {
		Text(
			text = stringResource(id = recommendationNameResourceId),
			style = MaterialTheme.typography.titleMedium,
			modifier = Modifier
				.padding(bottom = MaterialTheme.spacing.extraSmall)
		)
		Text(
			text = stringResource(id = recommendationAddressResourceId),
			style = MaterialTheme.typography.bodySmall,
		)
	}
}

@Composable
internal fun RecommendationList(
	recommendations: List<Recommendation>,
	onRecommendationSelected: (Recommendation) -> Unit,
	contentPadding: PaddingValues,
	modifier: Modifier = Modifier,
	selectedRecommendation: Recommendation = Recommendation(
		nameResourceId = 0,
		imageResourceId = 0,
		descriptionResourceId = 0,
		thumbnailResourceId = 0,
		addressResourceId = 0,
		category = RecommendationCategory.StartScreen
	),
	onBackClicked: () -> Unit = {},
) {
	BackHandler {
		onBackClicked()
	}
	LazyColumn(
		contentPadding = contentPadding,
		verticalArrangement = Arrangement.spacedBy(
			MaterialTheme.spacing.medium
		),
		modifier = modifier.padding(
			horizontal = MaterialTheme.spacing.small,
			vertical = MaterialTheme.spacing.medium
		)
	) {
		items(recommendations, key = { rec -> rec.nameResourceId }) { recommendation ->
			RecommendationItem(
				recommendation = recommendation,
				onRecommendationSelected = onRecommendationSelected,
				isSelected = recommendation == selectedRecommendation
			)
		}
	}
}

@Preview(showBackground = true)
@Composable
private fun RecommendationItemPreview() {
	CairoCityTheme {
		RecommendationItem(
			recommendation = Recommendation(
				nameResourceId = R.string.park_name_1,
				imageResourceId = R.drawable.park_1,
				descriptionResourceId = R.string.park_description_1,
				thumbnailResourceId = R.drawable.park_1_thumb,
				addressResourceId = R.string.park_address_1,
				category = RecommendationCategory.Parks
			),
			onRecommendationSelected = {},
			modifier = Modifier,
			isSelected = false
		)
	}
}

@Preview(showBackground = true)
@Composable
private fun RecommendationListPreview() {
	CairoCityTheme {
		RecommendationList(
			recommendations = listOf(
				Recommendation(
					nameResourceId = R.string.park_name_1,
					imageResourceId = R.drawable.park_1,
					descriptionResourceId = R.string.park_description_1,
					thumbnailResourceId = R.drawable.park_1_thumb,
					addressResourceId = R.string.park_address_1,
					category = RecommendationCategory.Parks
				),
				Recommendation(
					nameResourceId = R.string.park_name_2,
					imageResourceId = R.drawable.park_2,
					descriptionResourceId = R.string.park_description_2,
					thumbnailResourceId = R.drawable.park_2_thumb,
					addressResourceId = R.string.park_address_2,
					category = RecommendationCategory.Parks
				),
				Recommendation(
					nameResourceId = R.string.park_name_3,
					imageResourceId = R.drawable.park_3,
					descriptionResourceId = R.string.park_description_3,
					thumbnailResourceId = R.drawable.park_3_thumb,
					addressResourceId = R.string.park_address_3,
					category = RecommendationCategory.Parks
				)
			),
			onRecommendationSelected = {},
			selectedRecommendation = Recommendation(
				nameResourceId = R.string.park_name_1,
				imageResourceId = R.drawable.park_1,
				descriptionResourceId = R.string.park_description_1,
				thumbnailResourceId = R.drawable.park_1_thumb,
				addressResourceId = R.string.park_address_1,
				category = RecommendationCategory.Parks
			),
			contentPadding = PaddingValues()
		)
	}
}