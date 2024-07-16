package com.example.bookshelf.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.bookshelf.R

val OpenSans = FontFamily(
	Font(R.font.open_sans_regular, FontWeight.Normal),
	Font(R.font.open_sans_medium, FontWeight.Medium),
	Font(R.font.open_sans_bold, FontWeight.Bold)
)

val Typography = Typography(
	displayLarge = TextStyle(
		fontFamily = OpenSans,
		fontWeight = FontWeight.Medium,
		fontSize = 44.sp
	),
	displayMedium = TextStyle(
		fontFamily = OpenSans,
		fontWeight = FontWeight.Medium,
		fontSize = 40.sp
	),
	displaySmall = TextStyle(
		fontFamily = OpenSans,
		fontWeight = FontWeight.Medium,
		fontSize = 36.sp
	),
	headlineLarge = TextStyle(
		fontFamily = OpenSans,
		fontWeight = FontWeight.Bold,
		fontSize = 32.sp
	),
	headlineMedium = TextStyle(
		fontFamily = OpenSans,
		fontWeight = FontWeight.Bold,
		fontSize = 28.sp
	),
	headlineSmall = TextStyle(
		fontFamily = OpenSans,
		fontWeight = FontWeight.Bold,
		fontSize = 24.sp
	),
	titleLarge = TextStyle(
		fontFamily = OpenSans,
		fontWeight = FontWeight.Bold,
		fontSize = 20.sp
	),
	titleMedium = TextStyle(
		fontFamily = OpenSans,
		fontWeight = FontWeight.Bold,
		fontSize = 18.sp
	),
	titleSmall = TextStyle(
		fontFamily = OpenSans,
		fontWeight = FontWeight.Bold,
		fontSize = 16.sp
	),
	bodyLarge = TextStyle(
		fontFamily = OpenSans,
		fontWeight = FontWeight.Normal,
		fontSize = 18.sp
	),
	bodyMedium = TextStyle(
		fontFamily = OpenSans,
		fontWeight = FontWeight.Normal,
		fontSize = 16.sp
	),
	bodySmall = TextStyle(
		fontFamily = OpenSans,
		fontWeight = FontWeight.Normal,
		fontSize = 14.sp
	),
	labelLarge = TextStyle(
		fontFamily = OpenSans,
		fontWeight = FontWeight.Medium,
		fontSize = 16.sp
	),
	labelMedium = TextStyle(
		fontFamily = OpenSans,
		fontWeight = FontWeight.Medium,
		fontSize = 14.sp
	),
	labelSmall = TextStyle(
		fontFamily = OpenSans,
		fontWeight = FontWeight.Medium,
		fontSize = 12.sp
	),
)