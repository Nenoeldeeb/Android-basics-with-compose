package com.example.monthofcooking

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.monthofcooking.model.Recipe

@Composable
fun RecipeItem(recipe: Recipe, dayNumber: String, expanded: Boolean, onExpandedChange: (Boolean) -> Unit,
               modifier: Modifier = Modifier
) {
	val color by animateColorAsState(targetValue = if (expanded) MaterialTheme.colorScheme.tertiaryContainer
	else MaterialTheme.colorScheme.primaryContainer)
	Card(elevation = CardDefaults.cardElevation(defaultElevation = dimensionResource(id = R.dimen.card_elevation)),
	     modifier = modifier
		     .fillMaxWidth()
		     .padding(dimensionResource(id = R.dimen.medium_padding))
		     .clickable { onExpandedChange(!expanded) }) {
		Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
			.fillMaxWidth()
			.animateContentSize(
				animationSpec = spring(dampingRatio = Spring.DampingRatioNoBouncy, stiffness = Spring.StiffnessVeryLow))
			.background(color = color)) {
			Text(text = dayNumber, style = MaterialTheme.typography.displayMedium,
			     modifier = Modifier.padding(top = dimensionResource(id = R.dimen.small_padding),
			                                 bottom = dimensionResource(id = R.dimen.small_padding)))
			Box(modifier = Modifier
				.size(dimensionResource(id = R.dimen.image_width))
				.clip(MaterialTheme.shapes.medium)
				.align(Alignment.CenterHorizontally)) {
				Image(
					painter = painterResource(id = recipe.imageResourceId),
					contentDescription = stringResource(id = recipe.nameResourceId),
				)
			}
			Text(text = stringResource(id = recipe.nameResourceId), style = MaterialTheme.typography.bodyLarge,
			     modifier = Modifier.padding(top = dimensionResource(id = R.dimen.small_padding),
			                                 bottom = dimensionResource(id = R.dimen.small_padding)))
			if (expanded) {
				Text(text = stringResource(id = recipe.descriptionResourceId), style = MaterialTheme.typography.bodyMedium,
				     textAlign = TextAlign.Center, modifier = Modifier.padding(dimensionResource(id = R.dimen.small_padding)))
			}
		}
	}
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun RecipesList(recipes: List<Recipe>, modifier: Modifier = Modifier
) {
	val expandedState = remember { mutableStateListOf<Boolean>().apply { repeat(recipes.size) { add(false) } } }
	
	val visibleState = remember {
		MutableTransitionState(false).apply {
			// Start the animation immediately.
			targetState = true
		}
	}
	
	// Fade in entry animation for the entire list
	AnimatedVisibility(visibleState = visibleState,
	                   enter = fadeIn(animationSpec = spring(dampingRatio = Spring.DampingRatioLowBouncy)), exit = fadeOut(),
	                   modifier = modifier) {
		LazyColumn {
			itemsIndexed(recipes) { index, recipe ->
				RecipeItem(recipe = recipe, dayNumber = "Day ${index + 1}", expanded = expandedState[index],
				           onExpandedChange = { expandedState[index] = it },
				           modifier = Modifier
					           .padding(horizontal = 16.dp, vertical = 8.dp)
					           // Animate each list item to slide in vertically
					           .animateEnterExit(enter = slideInVertically(
						           animationSpec = spring(stiffness = Spring.StiffnessVeryLow,
						                                  dampingRatio = Spring.DampingRatioLowBouncy),
						           initialOffsetY = { it * (index + 1) } // staggered entrance
					           )))
			}
		}
	}
}