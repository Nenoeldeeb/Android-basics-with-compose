package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			LemonadeTheme {
				// A surface container using the 'background' color from the theme
				Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
					LemonadeApp()
				}
			}
		}
	}
}

@Composable
fun Lemonade(modifier: Modifier = Modifier) {
	
	var state by remember { mutableStateOf("lemonTree") }
	var instruction by remember { mutableStateOf(R.string.select_lemon) }
	var icon by remember { mutableStateOf(R.drawable.lemon_tree) }
	var description by remember { mutableStateOf(R.string.lemon_tree) }
	var squeezeNumber by remember { mutableStateOf(0) }
	
	Column(
		modifier = modifier.fillMaxSize()
			.padding(8.dp),
		verticalArrangement = Arrangement.Center,
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		Button(
			onClick = {
				when (state){
					"lemonTree" -> {
						icon = R.drawable.lemon_squeeze
						instruction = R.string.squeeze_lemon
						description = R.string.lemon
						state = "lemonSqueezing"
						squeezeNumber = (1..6).random()
					}
					
					"lemonSqueezing" -> {
						if (squeezeNumber == 0){
							icon = R.drawable.lemon_drink
							instruction = R.string.drink_lemon
							description = R.string.glass_of_lemonade
							state = "lemonadeDrink"
						}
						else
							squeezeNumber--
					}
					
					"lemonadeDrink" -> {
						icon = R.drawable.lemon_restart
						instruction = R.string.start_again
						description = R.string.empty_glass
						state = "return"
					}
					
					else -> {
						icon = R.drawable.lemon_tree
						instruction = R.string.select_lemon
						description = R.string.lemon_tree
						state = "lemonTree"
					}
				}
			},
			shape = MaterialTheme.shapes.extraLarge,
			modifier = modifier.size(136.dp)
			) {
			Image(
				painter = painterResource(id = icon),
				contentDescription = stringResource(id = description)
			)
		}
		Spacer(modifier = modifier.height(20.dp))
		Text(
			text = stringResource(id = instruction),
			fontSize = 20.sp,
			textAlign = TextAlign.Center,
			modifier = modifier,
		)
	}
	
}

@Preview(showBackground = true)
@Composable
fun LemonadeApp() {
	LemonadeTheme {
		Lemonade(modifier = Modifier)
	}
}