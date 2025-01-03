/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.tiptime

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tiptime.ui.theme.TipTimeTheme
import java.text.NumberFormat

class MainActivity : ComponentActivity() {
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			TipTimeTheme {
				// A surface container using the 'background' color from the theme
				Surface(
					modifier = Modifier.fillMaxSize(),
					color = MaterialTheme.colorScheme.background
				) {
					TipTimeLayout()
				}
			}
		}
	}
}

@Composable
fun TipTimeLayout() {
	
	var billAmount by remember { mutableStateOf("") }
	var percentage by remember { mutableStateOf("") }
	var roundUp by remember { mutableStateOf(false) }
	val tipAmount = calculateTip(amount = billAmount.toDoubleOrNull() ?: 0.0, tipPercent = percentage.toDoubleOrNull() ?: 0.0,
	                             roundUp = roundUp)
	
	Column(
		modifier = Modifier
			.padding(40.dp)
			.verticalScroll(rememberScrollState()),
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.Center
	) {
		Text(
			text = stringResource(R.string.calculate_tip),
			modifier = Modifier
				.padding(bottom = 16.dp)
				.align(alignment = Alignment.Start)
		)
		EditNumberField(
			value = billAmount,
			onValueChange = { billAmount = it },
			label = R.string.bill_amount,
			leadingIcon = R.drawable.money,
			keyboardOptions = KeyboardOptions.Default.copy(
				keyboardType = KeyboardType.Number,
				imeAction = ImeAction.Next
			),
			modifier = Modifier
				.padding(bottom = 32.dp)
				.fillMaxWidth()
		)
		EditNumberField(
			value = percentage,
			onValueChange = { percentage = it },
			label = R.string.how_was_the_service,
			leadingIcon = R.drawable.percent,
			keyboardOptions = KeyboardOptions.Default.copy(
				keyboardType = KeyboardType.Number,
				imeAction = ImeAction.Done
			),
			modifier = Modifier
				.padding(bottom = 32.dp)
				.fillMaxWidth()
		)
		RoundTheTipRow(
			roundUp = roundUp,
			onRoundUpChanged = { roundUp = it },
			modifier = Modifier.padding(bottom = 32.dp)
		)
		Text(
			text = stringResource(R.string.tip_amount, tipAmount),
			style = MaterialTheme.typography.displaySmall
		)
		Spacer(modifier = Modifier.height(150.dp))
	}
}

/**
 * Calculates the tip based on the user input and format the tip amount
 * according to the local currency.
 * Example would be "$10.00".
 */
private fun calculateTip(
	amount: Double,
	tipPercent: Double = 15.0,
	roundUp: Boolean
): String {
	
	val tip = tipPercent / 100 * amount
	
	if (roundUp) return NumberFormat.getCurrencyInstance().format(kotlin.math.ceil(tip))
	
	return NumberFormat.getCurrencyInstance().format(tip)
}

@Composable
private fun EditNumberField(
	value: String,
	onValueChange: (String) -> Unit,
	@StringRes label: Int,
	@DrawableRes leadingIcon: Int,
	keyboardOptions: KeyboardOptions,
	modifier: Modifier = Modifier
) {
	
	TextField(
		value = value,
		onValueChange = onValueChange,
		label = { Text(text = stringResource(id = label)) },
		leadingIcon = { Icon(painter = painterResource(id = leadingIcon), contentDescription = null) },
		singleLine = true,
		keyboardOptions = keyboardOptions,
		modifier = modifier,
		)
}

@Composable
private fun RoundTheTipRow(
	roundUp: Boolean,
	onRoundUpChanged: (Boolean) -> Unit,
	modifier: Modifier = Modifier
) {
	Row(
		modifier = modifier
			.fillMaxWidth()
			.size(48.dp),
		verticalAlignment = Alignment.CenterVertically
	) {
		Text(text = stringResource(R.string.round_up_tip))
		Switch(
			checked = roundUp,
			onCheckedChange = onRoundUpChanged,
			modifier = modifier
				.fillMaxWidth()
				.wrapContentWidth(Alignment.End)
		)
	}
}


@Preview(showBackground = true)
@Composable
fun TipTimeLayoutPreview() {
	TipTimeTheme {
		TipTimeLayout()
	}
}
