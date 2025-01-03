/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.lunchtray

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.lunchtray.datasource.DataSource
import com.example.lunchtray.ui.AccompanimentMenuScreen
import com.example.lunchtray.ui.CheckoutScreen
import com.example.lunchtray.ui.EntreeMenuScreen
import com.example.lunchtray.ui.OrderViewModel
import com.example.lunchtray.ui.SideDishMenuScreen
import com.example.lunchtray.ui.StartOrderScreen

// TODO: Screen enum
enum class LunchTrayScreens(@StringRes val title: Int) {
	
	Start(R.string.start_order),
	EntreeMenu(R.string.choose_entree),
	SideDishMenu(R.string.choose_side_dish),
	AccompanimentMenu(R.string.choose_accompaniment),
	Checkout(R.string.order_checkout)
}

// TODO: AppBar
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
	currentScreen: LunchTrayScreens,
	isNavigationIconVisible: Boolean,
	onNavigationIconClick: () -> Unit,
	modifier: Modifier = Modifier
) {
	CenterAlignedTopAppBar(
		title = { Text(stringResource(currentScreen.title)) },
		navigationIcon = {
			if (isNavigationIconVisible) {
				IconButton(onClick = { onNavigationIconClick() }) {
					Icon(
						imageVector = Icons.Filled.ArrowBack,
						contentDescription = stringResource(id =R.string.back_button)
					)
				}
			}
		},
		modifier = modifier
	)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LunchTrayApp() {
	// TODO: Create Controller and initialization
	val navController = rememberNavController()
	val backStackEntry by navController.currentBackStackEntryAsState()
	val currentScreen = LunchTrayScreens.valueOf(
		backStackEntry?.destination?.route ?: LunchTrayScreens.Start.name
	)
	
	// Create ViewModel
	val viewModel: OrderViewModel = viewModel()
	
	Scaffold(
		topBar = {
			// TODO: AppBar
			AppBar(
				currentScreen = currentScreen,
				isNavigationIconVisible = navController.previousBackStackEntry != null,
				onNavigationIconClick = { navController.navigateUp() }
			)
		}
	) { innerPadding ->
		val uiState by viewModel.uiState.collectAsState()
		
		// TODO: Navigation host
		NavHost(
			navController = navController,
			startDestination = LunchTrayScreens.Start.name,
			modifier = Modifier.padding(innerPadding)
		) {
			composable(LunchTrayScreens.Start.name) {
				StartOrderScreen(
					onStartOrderButtonClicked = { navController.navigate(LunchTrayScreens.EntreeMenu.name) },
					modifier = Modifier
						.fillMaxSize()
						.padding(dimensionResource(id = R.dimen.padding_medium))
				)
			}
			composable(LunchTrayScreens.EntreeMenu.name) {
				EntreeMenuScreen(
					options = DataSource.entreeMenuItems,
					onCancelButtonClicked = { backToStart(
						viewModel = viewModel,
						navController = navController
					) },
					onNextButtonClicked = { navController.navigate(LunchTrayScreens.SideDishMenu.name) },
					onSelectionChanged = { viewModel.updateEntree(it) },
					modifier = Modifier
						.fillMaxSize()
						.padding(dimensionResource(id = R.dimen.padding_medium))
				)
			}
			composable(LunchTrayScreens.SideDishMenu.name) {
				SideDishMenuScreen(
					options = DataSource.sideDishMenuItems,
					onCancelButtonClicked = { backToStart(
						viewModel = viewModel,
						navController = navController
					) },
					onNextButtonClicked = { navController.navigate(LunchTrayScreens.AccompanimentMenu.name) },
					onSelectionChanged = { viewModel.updateSideDish(it) },
					modifier = Modifier
						.fillMaxSize()
						.padding(dimensionResource(id = R.dimen.padding_medium))
				)
			}
			composable(LunchTrayScreens.AccompanimentMenu.name) {
				AccompanimentMenuScreen(
					options = DataSource.accompanimentMenuItems,
					onCancelButtonClicked = { backToStart(
						viewModel = viewModel,
						navController = navController
					) },
					onNextButtonClicked = { navController.navigate(LunchTrayScreens.Checkout.name) },
					onSelectionChanged = { viewModel.updateAccompaniment(it) },
					modifier = Modifier
						.fillMaxSize()
						.padding(dimensionResource(id = R.dimen.padding_medium))
				)
			}
			composable(LunchTrayScreens.Checkout.name) {
				CheckoutScreen(
					orderUiState = uiState,
					onNextButtonClicked = { backToStart(
						viewModel = viewModel,
						navController = navController
					) },
					onCancelButtonClicked = { backToStart(
						viewModel = viewModel,
						navController = navController
					) },
					modifier = Modifier
						.fillMaxSize()
						.padding(dimensionResource(id = R.dimen.padding_medium))
				)
			}
		}
	}
}

private fun backToStart(
	viewModel: OrderViewModel,
	navController: NavController
) {
	viewModel.resetOrder()
	navController.popBackStack(LunchTrayScreens.Start.name, false)
}
