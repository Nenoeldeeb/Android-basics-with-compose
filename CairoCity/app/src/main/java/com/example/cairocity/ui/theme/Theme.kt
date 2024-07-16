package com.example.cairocity.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat


private val LightColorScheme = lightColorScheme(
	primary = blue50,
	onPrimary = blue90,
	primaryContainer = blue70,
	onPrimaryContainer = blue10,
	inversePrimary = blue80,
	secondary = darkBlue50,
	onSecondary = darkBlue90,
	secondaryContainer = darkBlue70,
	onSecondaryContainer = darkBlue10,
	tertiary = green50,
	onTertiary = green90,
	tertiaryContainer = green70,
	onTertiaryContainer = green10,
	background = gray90,
	onBackground = gray10,
	surface = gray80,
	onSurface = gray10,
	surfaceVariant = gray70,
	onSurfaceVariant = gray10,
	surfaceTint = gray60,
	inverseSurface = gray20,
	inverseOnSurface = gray90,
	error = red50,
	onError = red90,
	errorContainer = red70,
	onErrorContainer = red10,
	outline = green70,
	outlineVariant = green60,
	scrim = blue10.copy(alpha = 0.6f)
)


private val DarkColorScheme = darkColorScheme(
	primary = blue50,
	onPrimary = blue90,
	primaryContainer = blue30,
	onPrimaryContainer = blue90,
	inversePrimary = blue70,
	secondary = darkBlue50,
	onSecondary = darkBlue90,
	secondaryContainer = darkBlue30,
	onSecondaryContainer = darkBlue90,
	tertiary = green50,
	onTertiary = green90,
	tertiaryContainer = green30,
	onTertiaryContainer = green90,
	background = gray10,
	onBackground = gray90,
	surface = gray20,
	onSurface = gray90,
	surfaceVariant = gray30,
	onSurfaceVariant = gray90,
	surfaceTint = gray40,
	inverseSurface = gray70,
	inverseOnSurface = gray10,
	error = red50,
	onError = red90,
	errorContainer = red20,
	onErrorContainer = red90,
	outline = green40,
	outlineVariant = green30,
	scrim = blue10.copy(alpha = 0.6f)
)

@Composable
fun CairoCityTheme(
	darkTheme: Boolean = isSystemInDarkTheme(),
	// Dynamic color is available on Android 12+
	dynamicColor: Boolean = true,
	content: @Composable () -> Unit
) {
	val colorScheme = when {
		dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
			val context = LocalContext.current
			if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
		}
		
		darkTheme -> DarkColorScheme
		else -> LightColorScheme
	}
	val view = LocalView.current
	if (!view.isInEditMode) {
		SideEffect {
			val window = (view.context as Activity).window
			window.statusBarColor = colorScheme.primary.toArgb()
			if (dynamicColor)
				WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
		}
	}
	
	CompositionLocalProvider(
		LocalSpacing provides Spacing()
	) {
		MaterialTheme(
			colorScheme = colorScheme,
			shapes = shapes,
			typography = Typography,
			content = content
		)
	}
}