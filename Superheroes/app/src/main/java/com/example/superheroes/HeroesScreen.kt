package com.example.superheroes

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.Spring.DampingRatioLowBouncy
import androidx.compose.animation.core.Spring.StiffnessVeryLow
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.superheroes.model.Superhero


@Composable
fun HeroItem(
	superhero: Superhero,
	modifier: Modifier = Modifier
) {
	Card(
		elevation = CardDefaults.cardElevation(defaultElevation = dimensionResource(id = R.dimen.card_elevation)),
		modifier = modifier
	) {
		Row(
			modifier = Modifier
				.fillMaxWidth()
				.sizeIn(minHeight = dimensionResource(id = R.dimen.item_height))
				.padding(dimensionResource(id = R.dimen.medium_padding))
				.clip(MaterialTheme.shapes.medium)
		) {
			Column(modifier = Modifier.weight(1f)) {
				Text(
					text = stringResource(id = superhero.nameResourceId),
					style = MaterialTheme.typography.displaySmall
				)
				Text(
					text = stringResource(id = superhero.descriptionResourceId),
					style = MaterialTheme.typography.bodyLarge
				)
			}
			Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.medium_padding)))
			Box(
				modifier = Modifier
					.size(dimensionResource(id = R.dimen.image_size))
					.clip(MaterialTheme.shapes.small)
			) {
				Image(
					painter = painterResource(id = superhero.imageResourceId),
					contentDescription = stringResource(id = superhero.nameResourceId),
					contentScale = ContentScale.FillWidth,
					alignment = Alignment.TopCenter
				)
			}
		}
	}
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun HeroesList(
	superheroes: List<Superhero>,
	modifier: Modifier = Modifier
) {
	val visibleState = remember {
		MutableTransitionState(false).apply {
			// Start the animation immediately.
			targetState = true
		}
	}
	
	// Fade in entry animation for the entire list
	AnimatedVisibility(
		visibleState = visibleState,
		enter = fadeIn(
			animationSpec = spring(dampingRatio = DampingRatioLowBouncy)
		),
		exit = fadeOut(),
		modifier = modifier
	) {
		LazyColumn {
			itemsIndexed(superheroes) { index, hero ->
				HeroItem(
					superhero = hero,
					modifier = Modifier
						.padding(horizontal = 16.dp, vertical = 8.dp)
						// Animate each list item to slide in vertically
						.animateEnterExit(
							enter = slideInVertically(
								animationSpec = spring(
									stiffness = StiffnessVeryLow,
									dampingRatio = DampingRatioLowBouncy
								),
								initialOffsetY = { it * (index + 1) } // staggered entrance
							)
						)
				)
			}
		}
	}
}