package com.example.reply.test

import androidx.activity.ComponentActivity
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.example.reply.ui.ReplyApp
import com.example.reply.R
import org.junit.Rule
import org.junit.Test

class ReplyAppTest {
	
	@get:Rule
	val composeTestRule = createAndroidComposeRule<ComponentActivity>()
	
	@Test
	@TestCompactWidth
	fun compactDevice_verifyBottomNavigation() {
		composeTestRule.setContent {
			ReplyApp(windowSize = WindowWidthSizeClass.Compact)
		}
		// Verify the bottom navigation bar
		composeTestRule.onNodeWithTagForStringId(R.string.navigation_bottom)
			.assertExists()
	}
	
	@Test
	@TestMediumWidth
	fun mediumDevice_verifyNavigationRail() {
		composeTestRule.setContent {
			ReplyApp(windowSize = WindowWidthSizeClass.Medium)
		}
		// Verify the navigation rail
		composeTestRule.onNodeWithTagForStringId(R.string.navigation_rail)
			.assertExists()
	}
	
	@Test
	@TestExpandedWidth
	fun expandedDevice_verifyPermanentNavigationDrawer() {
		composeTestRule.setContent {
			ReplyApp(windowSize = WindowWidthSizeClass.Expanded)
		}
		// Verify the permanent navigation drawer
		composeTestRule.onNodeWithTagForStringId(R.string.navigation_drawer)
			.assertExists()
	}
}