package com.example.cairocity.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cairocity.R
import com.example.cairocity.ui.theme.CairoCityTheme
import com.example.cairocity.ui.theme.spacing
import com.example.cairocity.utils.RecommendationCategory

@Composable
fun StartScreen(
	onClick: (RecommendationCategory) -> Unit,
	contentPadding: PaddingValues,
	modifier: Modifier = Modifier
) {
	Column(
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.Center,
		modifier = modifier
			.fillMaxSize()
			.padding(contentPadding)
	) {
		Button(onClick = { onClick(RecommendationCategory.CoffeeShops) }) {
			Row(
				horizontalArrangement = Arrangement.Start,
				modifier = Modifier.width(200.dp)
			) {
				Icon(
					painter = painterResource(id = R.drawable.coffee_shops),
					contentDescription = stringResource(id = R.string.category_coffee_shops)
				)
				Spacer(modifier = Modifier.width(MaterialTheme.spacing.medium))
				Text(stringResource(id = R.string.category_coffee_shops))
			}
		}
		Button(onClick = { onClick(RecommendationCategory.Restaurants) }) {
			Row(
				horizontalArrangement = Arrangement.Start,
				modifier = Modifier.width(200.dp)
			) {
				Icon(
					painter = painterResource(id = R.drawable.restaurants),
					contentDescription = stringResource(id = R.string.category_restaurants)
				)
				Spacer(modifier = Modifier.width(MaterialTheme.spacing.medium))
				Text(stringResource(id = R.string.category_restaurants))
			}
		}
		Button(onClick = { onClick(RecommendationCategory.Parks) }) {
			Row(
				horizontalArrangement = Arrangement.Start,
				modifier = Modifier.width(200.dp)
			) {
				Icon(
					painter = painterResource(id = R.drawable.parks),
					contentDescription = stringResource(id = R.string.category_parks)
				)
				Spacer(modifier = Modifier.width(MaterialTheme.spacing.medium))
				Text(stringResource(id = R.string.category_parks))
			}
		}
		Button(onClick = { onClick(RecommendationCategory.KidsAreas) }) {
			Row(
				horizontalArrangement = Arrangement.Start,
				modifier = Modifier.width(200.dp)
			) {
				Icon(
					painter = painterResource(id = R.drawable.kids_areas),
					contentDescription = stringResource(id = R.string.category_kids_areas)
				)
				Spacer(modifier = Modifier.width(MaterialTheme.spacing.medium))
				Text(stringResource(id = R.string.category_kids_areas))
			}
		}
		Button(onClick = { onClick(RecommendationCategory.ShoppingCenters) }) {
			Row(
				horizontalArrangement = Arrangement.Start,
				modifier = Modifier.width(200.dp)
			) {
				Icon(
					painter = painterResource(id = R.drawable.shopping_centers),
					contentDescription = stringResource(id = R.string.category_shopping_centers)
				)
				Spacer(modifier = Modifier.width(MaterialTheme.spacing.medium))
				Text(stringResource(id = R.string.category_shopping_centers))
			}
		}
	}
}

@Preview
@Composable
private fun StartScreenPreview() {
	CairoCityTheme {
		StartScreen(
			onClick = {},
			contentPadding = PaddingValues(16.dp)
		)
	}
}