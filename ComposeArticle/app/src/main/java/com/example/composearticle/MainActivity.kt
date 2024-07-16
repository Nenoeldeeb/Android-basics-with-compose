package com.example.composearticle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composearticle.ui.theme.ComposeArticleTheme

class MainActivity : ComponentActivity() {
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			ComposeArticleTheme {
				// A surface container using the 'background' color from the theme
				Surface(modifier = Modifier.fillMaxSize(),
				        color = MaterialTheme.colorScheme.background) {
					ArticleScreen(
						image = painterResource(id = R.drawable.bg_compose_background),
						
						title = stringResource(id = R.string.article_title),
						brief = stringResource(id = R.string.article_brief),
						body = stringResource(id = R.string.article_body)
					)
				}
			}
		}
	}
}

@Composable
fun ArticleScreen(modifier: Modifier = Modifier, image: Painter, title: String, brief: String, body: String) {
	Column(modifier = modifier) {
		Image(
			painter = image,
			contentDescription = null,
			modifier = Modifier.fillMaxWidth()
		)
		Text(
			text = title,
			modifier.padding(16.dp),
			fontSize = 24.sp
		)
		Text(
			text = brief,
			modifier.padding(16.dp),
			textAlign = TextAlign.Justify
		)
		Text(
			text = body,
			modifier.padding(16.dp),
			textAlign = TextAlign.Justify
		)
	}
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
	ComposeArticleTheme {
		ArticleScreen(
			image = painterResource(id = R.drawable.bg_compose_background),
			title = stringResource(id = R.string.article_title),
			brief = stringResource(id = R.string.article_brief),
			body = stringResource(id = R.string.article_body)
		)
		
	}
}