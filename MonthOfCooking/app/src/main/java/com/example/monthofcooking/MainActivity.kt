package com.example.monthofcooking

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.monthofcooking.data.RecipesRepository.allRecipes
import com.example.monthofcooking.model.Recipe
import com.example.monthofcooking.ui.theme.MonthOfCookingTheme

class MainActivity : ComponentActivity() {
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			MonthOfCookingTheme {
				// A surface container using the 'background' color from the theme
				Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
					MonthOfCookingApp()
				}
			}
		}
	}
	
	
	@OptIn(ExperimentalMaterial3Api::class)
	@Composable
	fun MonthOfCookingApp(recipes: List<Recipe> = allRecipes) {
		
		Scaffold(topBar = { TopAppBar() }, modifier = Modifier.fillMaxSize()) { insets ->
			RecipesList(recipes = recipes, modifier = Modifier.padding(insets))
		}
	}
	
	@OptIn(ExperimentalMaterial3Api::class)
	@Composable
	fun TopAppBar(modifier: Modifier = Modifier) {
		CenterAlignedTopAppBar(title = {
			Text(text = stringResource(id = R.string.app_name), style = MaterialTheme.typography.displayLarge)
		}, modifier = modifier)
	}
}