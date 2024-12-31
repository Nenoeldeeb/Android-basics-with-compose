package com.example.cupcake.test

import androidx.navigation.NavController
import org.junit.Assert.assertEquals

fun NavController.assertCurrentRouteName(routeName: String) {
	assertEquals(routeName, currentBackStackEntry?.destination?.route)
}