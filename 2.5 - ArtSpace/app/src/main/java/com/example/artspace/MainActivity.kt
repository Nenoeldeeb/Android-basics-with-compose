package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			ArtSpaceTheme {
				// A surface container using the 'background' color from the theme
				Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
					ArtSpaceLayout(modifier = Modifier)
				}
			}
		}
	}
}

@Composable
fun ArtSpaceLayout(modifier: Modifier = Modifier) {
	
	var index by rememberSaveable { mutableStateOf(1) }
	var nextState by rememberSaveable { mutableStateOf(true) }
	var previousState by rememberSaveable { mutableStateOf(false) }
	
	var image by remember { mutableStateOf(R.drawable.image1) }
	var title by remember { mutableStateOf(R.string.title1) }
	var author by remember { mutableStateOf(R.string.author1) }
	var year by remember { mutableStateOf(R.string.year1) }
	
	when (index) {
		1 -> {
			image = R.drawable.image1
			title = R.string.title1
			author = R.string.author1
			year = R.string.year1
			previousState = false
		}
		
		2 -> {
			image = R.drawable.image2
			title = R.string.title2
			author = R.string.author2
			year = R.string.year2
			previousState = true
		}
		
		3 -> {
			image = R.drawable.image3
			title = R.string.title3
			author = R.string.author3
			year = R.string.year3
		}
		
		4 -> {
			image = R.drawable.image4
			title = R.string.title4
			author = R.string.author4
			year = R.string.year4
			nextState = true
		}
		
		5 -> {
			image = R.drawable.image5
			title = R.string.title5
			author = R.string.author5
			year = R.string.year5
			nextState = false
		}
	}
	
	Column(
		verticalArrangement = Arrangement.Center,
		horizontalAlignment = Alignment.CenterHorizontally,
		modifier = modifier
			.fillMaxSize()
			.padding(8.dp)
			.verticalScroll(rememberScrollState())
	) {
		ArtWorkWall(
			image = image,
			modifier = modifier.padding(8.dp)
		)
		ArtWorkDescriptor(
			title = title,
			author = author,
			year = year,
			modifier = modifier.padding(8.dp)
		)
		ArtWorkController(
			onClickNext = {
				if (index < 5) {
					index++
				}
			},
			nextState = nextState,
			onClickPrevious = {
				if (index > 1) {
					index--
				}
			},
			previousState = previousState,
			modifier = modifier.padding(8.dp)
		)
	}
}

@Composable
private fun ArtWorkWall(
	@DrawableRes image: Int,
	modifier: Modifier = Modifier
) {
	Image(
		painter = painterResource(id = image),
		contentDescription = null,
		modifier = modifier
			.fillMaxWidth()
			.aspectRatio(1f)
			.clip(RoundedCornerShape(8.dp))
	)
}

@Composable
private fun ArtWorkDescriptor(
	@StringRes title: Int,
	@StringRes author: Int,
	@StringRes year: Int,
	modifier: Modifier = Modifier
) {
	Column(modifier = modifier) {
		Text(
			text = stringResource(id = title),
			style = MaterialTheme.typography.titleLarge
		)
		Row {
			Text(
				text = stringResource(id = author),
				fontWeight = FontWeight.Bold
			)
			Spacer(modifier = modifier)
			Text(text = "(${stringResource(id = year)})")
		}
	}
}

@Composable
private fun ArtWorkController(
	onClickNext: () -> Unit,
	nextState: Boolean,
	onClickPrevious: () -> Unit,
	previousState: Boolean,
	modifier: Modifier = Modifier
) {
	Row(
		horizontalArrangement = Arrangement.SpaceBetween,
		modifier = modifier
			.fillMaxWidth()
			.padding(top = 112.dp)
	) {
		Button(
			onClick = onClickPrevious,
			enabled = previousState,
			modifier = modifier.width(112.dp)
		) {
			Text(text = stringResource(id = R.string.previous))
		}
		Button(
			onClick = onClickNext,
			enabled = nextState,
			modifier = modifier.width(112.dp)
		) {
			Text(text = stringResource(id = R.string.next))
		}
	}
}
