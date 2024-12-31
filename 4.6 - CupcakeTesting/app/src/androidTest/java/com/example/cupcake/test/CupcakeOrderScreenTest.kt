package com.example.cupcake.test

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.cupcake.ui.SelectOptionScreen
import com.example.cupcake.R
import com.example.cupcake.data.DataSource
import com.example.cupcake.data.OrderUiState
import com.example.cupcake.ui.OrderSummaryScreen
import com.example.cupcake.ui.StartOrderScreen
import org.junit.Rule
import org.junit.Test

class CupcakeOrderScreenTest {
	
	@get:Rule
	val composeTestRule = createAndroidComposeRule<ComponentActivity>()
	
	@Test
	fun selectOptionScreen_verifyContent() {
		val flavors = listOf("Vanilla", "Chocolate", "Red Velvet", "Lemon")
		val subtotal = "$5.00"
		
		composeTestRule.setContent {
			SelectOptionScreen(
				options = flavors,
				subtotal = subtotal,
			)
		}
		
		flavors.forEach {
			composeTestRule.onNodeWithText(it).assertIsDisplayed()
		}
		
		composeTestRule.onNodeWithText(
			composeTestRule.activity.getString(R.string.subtotal_price, subtotal)
		).assertIsDisplayed()
		
		composeTestRule.onNodeWithStringId(R.string.next).assertIsNotEnabled()
	}
	
	@Test
	fun startOrderScreen_verifyContent() {
		val quantityOptions = DataSource.quantityOptions
		composeTestRule.setContent {
			StartOrderScreen(quantityOptions = quantityOptions, {})
		}
		
	quantityOptions.forEach {
		composeTestRule.onNodeWithStringId(it.first).assertIsDisplayed()
		}
	}
	
	@Test
	fun summaryScreen_verifyContent() {
		val fakeUiState = OrderUiState(
			quantity = 6,
			flavor = "Vanilla",
			date = "Mon Jan 12",
			price = "$5.00",
			pickupOptions = listOf()
		)

		composeTestRule.setContent {
			OrderSummaryScreen(
				orderUiState = fakeUiState,
				onCancelButtonClicked = {},
				onSendButtonClicked = {_,_ -> }
			)
		}
		
		composeTestRule.onNodeWithText("${fakeUiState.quantity} cupcakes").assertIsDisplayed()
		composeTestRule.onNodeWithText(fakeUiState.flavor).assertIsDisplayed()
		composeTestRule.onNodeWithText(fakeUiState.date).assertIsDisplayed()
		composeTestRule.onNodeWithText(
			composeTestRule.activity.getString(R.string.subtotal_price, fakeUiState.price)
		).assertIsDisplayed()
	}
	
	@Test
	fun selectOptionScreen_enableNextButton() {
		val flavors = listOf("Vanilla", "Chocolate", "Red Velvet", "Lemon")
		val subtotal = "$5.00"
		
		composeTestRule.setContent {
			SelectOptionScreen(
				options = flavors,
				subtotal = subtotal,
			)
		}
		
		composeTestRule.onNodeWithText("Vanilla").performClick()
		composeTestRule.onNodeWithStringId(R.string.next).assertIsEnabled()
	}
}