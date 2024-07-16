package com.example.courses

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.courses.model.Topic
import com.example.courses.data.DataSource
import com.example.courses.ui.theme.CoursesTheme

class MainActivity : ComponentActivity() {
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			CoursesTheme {
				TopicsLayout()
			}
		}
	}
}

@Composable
private fun TopicsLayout(modifier: Modifier = Modifier) {
	TopicsGrid(topics = DataSource.topics)
}

@Composable
private fun TopicCard(
	@StringRes title: Int,
	courses: Int,
	@DrawableRes image: Int,
	modifier: Modifier = Modifier
) {
	Card {
		Row {
			Image(
				painter = painterResource(id = image),
				contentDescription = null,
				contentScale = ContentScale.Crop,
				modifier = modifier
					.size(68.dp)
					.aspectRatio(1f)
			)
			Column(
				modifier = modifier.padding(
					top = dimensionResource(id = R.dimen.padding_medium),
					end = dimensionResource(id = R.dimen.padding_medium),
					start = dimensionResource(id = R.dimen.padding_medium),
				)) {
				Text(
					text = stringResource(id = title),
					style = MaterialTheme.typography.bodyMedium,
					modifier = modifier.padding(bottom = dimensionResource(id = R.dimen.padding_small))
				)
				Row {
					Icon(
						painter = painterResource(id = R.drawable.ic_grain),
						contentDescription = null,
						modifier = modifier.padding(end = dimensionResource(id = R.dimen.padding_small))
					)
					Text(
						text = courses.toString(),
						style = MaterialTheme.typography.labelMedium
					)
				}
			}
		}
	}
}

@Composable
private fun TopicsGrid(
	topics: List<Topic>,
	modifier: Modifier = Modifier
){
	LazyVerticalGrid(
		columns = GridCells.Fixed(2),
		verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small)),
		horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small)),
		) {
		items(topics){ topic ->
				TopicCard(title = topic.topicNameID,
				          courses = topic.associatedCourses,
				          image = topic.topicImageID
				)
			}
	}
}