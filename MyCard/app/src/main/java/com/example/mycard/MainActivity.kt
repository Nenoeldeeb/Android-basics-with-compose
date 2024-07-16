package com.example.mycard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mycard.ui.theme.MyCardTheme

class MainActivity : ComponentActivity() {
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			MyCardTheme {
				// A surface container using the 'background' color from the theme
				Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
					BussinessCard()
				}
			}
		}
	}
}

@Composable
fun BussinessCard() {
	Column(
		Modifier
			.fillMaxSize()
			.background(color = colorResource(id = R.color.red)),
		horizontalAlignment = CenterHorizontally,
		verticalArrangement = Arrangement.SpaceBetween
	) {
		PersonalInfo()
		CommunicationInfo()
	}
}

@Composable
fun PersonalInfo(modifier: Modifier = Modifier) {
	Column(
		horizontalAlignment = CenterHorizontally,
		modifier = Modifier.padding(top = 80.dp)
	) {
		Image(
			painter = painterResource(id = R.drawable.android_logo),
			contentDescription = "Android Logo"
		)
		Text(
			text = "Neno Eldeeb",
			style = MaterialTheme.typography.headlineLarge,
			fontWeight = FontWeight.Bold,
			modifier = Modifier.padding(bottom = 16.dp)
		)
		Text(
			text = "Android Developer",
			style = MaterialTheme.typography.bodyMedium,
			fontSize = 20.sp,
			color = colorResource(id = R.color.red_200)
		)
	}
}

@Composable
fun CommunicationInfo(modifier: Modifier = Modifier) {
	Column {
		ContactInfo(contact = "1234567890", icon = Icons.Default.Phone, type = "Phone")
		ContactInfo(contact = "neno@neno-dev.me", icon = Icons.Default.Email, type = "Email")
		ContactInfo(contact = "@Neno-Dev", icon = Icons.Default.Share, type = "Social Media")
	}
}

@Composable
fun ContactInfo(modifier: Modifier = Modifier, contact: String, icon: ImageVector, type: String) {
	Row(
		horizontalArrangement = Arrangement.Center,
		verticalAlignment = Alignment.CenterVertically,
		modifier = Modifier.padding(8.dp)
	) {
		Icon(
			imageVector = icon,
			contentDescription = type + " icon",
			modifier = Modifier
				.padding(end = 8.dp)
				.size(28.dp),
			tint = colorResource(id = R.color.red_200)
		)
		Text(
			text = contact,
			modifier = Modifier.padding(start = 8.dp),
			fontWeight = FontWeight.Bold,
			fontSize = 24.sp
		
		)
	}
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
	MyCardTheme {
		BussinessCard()
	}
}