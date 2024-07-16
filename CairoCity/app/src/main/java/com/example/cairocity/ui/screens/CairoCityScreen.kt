package com.example.cairocity.ui.screens

import android.app.Activity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cairocity.R
import com.example.cairocity.data.LocalRecommendationsProvider.getRecommendationsByCategory
import com.example.cairocity.model.Recommendation
import com.example.cairocity.ui.CairoCityUiState
import com.example.cairocity.ui.CairoCityViewModel
import com.example.cairocity.utils.RecommendationCategory
import com.example.cairocity.utils.RecommendationContentType

@Composable
fun CairoCityApp(
	windowSize: WindowSizeClass,
	modifier: Modifier = Modifier,
	viewModel: CairoCityViewModel = viewModel(),
) {
	val uiState by viewModel.uiState.collectAsState()
	val navController = rememberNavController()
	val contentType: RecommendationContentType =
		if (windowSize.widthSizeClass == WindowWidthSizeClass.Expanded) {
			RecommendationContentType.LISTANDDETAILS
		}
		else {
			RecommendationContentType.LISTONLY
		}
	
	Scaffold(
		topBar = {
			AppBar(
				uiState = uiState,
				showBackButton = navController.previousBackStackEntry != null,
				onBackClicked = { navController.popBackStack() },
				currentDestination = navController.currentBackStackEntry,
				modifier = modifier
			)
		},
		modifier = modifier
	) { padding ->
		if (contentType == RecommendationContentType.LISTONLY) {
			RecommendationListOnly(
				uiState = uiState,
				onRecommendationSelected = { viewModel.onRecommendationSelected(it) },
				onCategorySelected = { viewModel.onCategorySelected(it) },
				navController = navController,
				contentPadding = padding
			)
		}
		else {
			val context = LocalContext.current as Activity
			RecommendationListAndDetails(
				uiState = uiState,
				onRecommendationSelected = { viewModel.onRecommendationSelected(it) },
				onCategorySelected = { viewModel.onCategorySelected(it) },
				onBackClicked = { context.finish() },
				contentPadding = padding
			)
		}
	}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AppBar(
	uiState: CairoCityUiState,
	onBackClicked: () -> Unit,
	currentDestination: NavBackStackEntry?,
	modifier: Modifier = Modifier,
	showBackButton: Boolean,
) {
	CenterAlignedTopAppBar(
		title = {
			if (currentDestination?.destination?.route == RecommendationCategory.StartScreen.name) {
				Text(
					text = stringResource(id = RecommendationCategory.StartScreen.titleResourceId),
					style = MaterialTheme.typography.titleMedium
				)
			}
			else {
				Text(
					text = stringResource(id = uiState.currentCategory.titleResourceId),
					style = MaterialTheme.typography.titleMedium
				)
			}
		},
		navigationIcon = {
			if (showBackButton) {
				IconButton(onClick = { onBackClicked() }) {
					Icon(
						Icons.Default.ArrowBack,
						contentDescription = stringResource(id = R.string.back_button)
					)
				}
			}
		},
		colors = TopAppBarDefaults.topAppBarColors(
			containerColor = MaterialTheme.colorScheme.primary,
			titleContentColor = MaterialTheme.colorScheme.onPrimary),
		modifier = modifier
	)
}

@Composable
private fun RecommendationListOnly(
	uiState: CairoCityUiState,
	onRecommendationSelected: (Recommendation) -> Unit,
	onCategorySelected: (RecommendationCategory) -> Unit,
	navController: NavHostController,
	contentPadding: PaddingValues,
	modifier: Modifier = Modifier
) {
	NavHost(
		navController = navController,
		startDestination = RecommendationCategory.StartScreen.name,
		modifier = modifier
	) {
		composable(route = RecommendationCategory.StartScreen.name) {
			StartScreen(
				onClick = {
					navController.navigate(it.name)
					onCategorySelected(it)
				},
				contentPadding = contentPadding
			)
		}
		RecommendationCategory.entries.forEach { category ->
			if (category != RecommendationCategory.StartScreen
				&& category != RecommendationCategory.DetailsScreen
			) {
				composable(route = category.name) {
					RecommendationList(
						recommendations = getRecommendationsByCategory(category),
						onRecommendationSelected = {
							navController.navigate(RecommendationCategory.DetailsScreen.name)
							onRecommendationSelected(it)
						},
						onBackClicked = { navController.popBackStack() },
						contentPadding = contentPadding,
					)
				}
			}
		}
		composable(route = RecommendationCategory.DetailsScreen.name) {
			RecommendationDetails(
				recommendation = uiState.selectedRecommendation,
				onBackClicked = { navController.popBackStack() },
				contentPadding = contentPadding
			)
		}
	}
}

@Composable
private fun RecommendationListAndDetails(
	uiState: CairoCityUiState,
	onRecommendationSelected: (Recommendation) -> Unit,
	onCategorySelected: (RecommendationCategory) -> Unit,
	onBackClicked: () -> Unit,
	contentPadding: PaddingValues,
	modifier: Modifier = Modifier
) {
	RecommendationNavigationDrawer(
		onCategorySelected = { onCategorySelected(it) },
		uiState = uiState,
		modifier = modifier,
		contentPadding = contentPadding
	) {
		Row {
			RecommendationList(
				recommendations = uiState.currentRecommendations,
				onRecommendationSelected = { onRecommendationSelected(it) },
				contentPadding = contentPadding,
				selectedRecommendation = uiState.selectedRecommendation,
				modifier = Modifier
					.weight(2f)
			)
			RecommendationDetails(
				recommendation = uiState.selectedRecommendation,
				onBackClicked = { onBackClicked() },
				contentPadding = contentPadding,
				modifier = Modifier
					.weight(3f)
			)
		}
	}
}

@Composable
private fun RecommendationNavigationDrawer(
	onCategorySelected: (RecommendationCategory) -> Unit,
	uiState: CairoCityUiState,
	contentPadding: PaddingValues,
	modifier: Modifier = Modifier,
	content: @Composable () -> Unit
) {
	PermanentNavigationDrawer(
		drawerContent = {
			PermanentDrawerSheet(modifier = Modifier.width(230.dp)) {
				Column(modifier = Modifier.padding(contentPadding)) {
					NavigationDrawerItem(
						label = {
							Text(stringResource(R.string.category_coffee_shops))
						},
						icon = {
							Icon(
								painter = painterResource(id = R.drawable.coffee_shops),
								contentDescription = stringResource(R.string.category_coffee_shops)
							)
						},
						selected = uiState.selectedRecommendation.category ==
								RecommendationCategory.CoffeeShops,
						onClick = { onCategorySelected(RecommendationCategory.CoffeeShops) }
					)
					NavigationDrawerItem(
						label = {
							Text(stringResource(R.string.category_restaurants))
						},
						icon = {
							Icon(
								painter = painterResource(id = R.drawable.restaurants),
								contentDescription = stringResource(R.string.category_restaurants)
							)
						},
						selected = uiState.selectedRecommendation.category ==
								RecommendationCategory.Restaurants,
						onClick = { onCategorySelected(RecommendationCategory.Restaurants) }
					)
					NavigationDrawerItem(
						label = {
							Text(stringResource(R.string.category_parks))
						},
						icon = {
							Icon(
								painter = painterResource(id = R.drawable.parks),
								contentDescription = stringResource(R.string.category_parks)
							)
						},
						selected = uiState.selectedRecommendation.category ==
								RecommendationCategory.Parks,
						onClick = { onCategorySelected(RecommendationCategory.Parks) }
					)
					NavigationDrawerItem(
						label = {
							Text(stringResource(R.string.category_kids_areas))
						},
						icon = {
							Icon(
								painter = painterResource(id = R.drawable.kids_areas),
								contentDescription = stringResource(R.string.category_kids_areas)
							)
						},
						selected = uiState.selectedRecommendation.category ==
								RecommendationCategory.KidsAreas,
						onClick = { onCategorySelected(RecommendationCategory.KidsAreas) }
					)
					NavigationDrawerItem(
						label = {
							Text(stringResource(R.string.category_shopping_centers))
						},
						icon = {
							Icon(
								painter = painterResource(id = R.drawable.shopping_centers),
								contentDescription = stringResource(R.string.category_shopping_centers)
							)
						},
						selected = uiState.selectedRecommendation.category ==
								RecommendationCategory.ShoppingCenters,
						onClick = { onCategorySelected(RecommendationCategory.ShoppingCenters) }
					)
				}
			}
		},
		modifier = modifier) { content() }
}
