package com.example.tiptime

import org.junit.Assert.assertEquals
import org.junit.Test
import java.text.NumberFormat

class TipCalculatorTests {
	
	@Test
	fun calculateTip_20percentageNoRound () {
		val tipPercentage = 20.00
		val billAmount = 10.00
		val expectedTip = NumberFormat.getCurrencyInstance().format(2)
		val actualTip = calculateTip(amount = billAmount, tipPercent = tipPercentage, roundUp =  false)
		
		assertEquals(expectedTip, actualTip)
	}
}