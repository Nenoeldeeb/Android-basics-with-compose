package com.example.superheroes

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
import androidx.compose.ui.tooling.preview.Preview
import com.example.superheroes.model.AllSuperheroes
import com.example.superheroes.ui.theme.SuperheroesTheme

class MainActivity : ComponentActivity() {
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			SuperheroesTheme {
				// A surface container using the 'background' color from the theme
				Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
					SuperheroesApp()
				}
			}
		}
	}
	
	@OptIn(ExperimentalMaterial3Api::class)
	@Composable
	fun SuperheroesApp(){
		Scaffold (
			topBar = {
				TopAppBar()
			},
			modifier =  Modifier.fillMaxSize()
		) {insets ->
			HeroesList(superheroes = AllSuperheroes.allHeroes, modifier = Modifier.padding(insets))
		}
	}
	
	@OptIn(ExperimentalMaterial3Api::class)
	@Composable
	fun TopAppBar(modifier: Modifier = Modifier) {
		CenterAlignedTopAppBar(
			title = {
				Text(
					text = stringResource(id = R.string.app_name),
					style = MaterialTheme.typography.displayLarge
				)
			},
			modifier = modifier
		)
	}
}

