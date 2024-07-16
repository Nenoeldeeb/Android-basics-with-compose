package com.example.juicetracker.ui.homescreen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView

@Composable
fun AdBanner(modifier: Modifier = Modifier) {
	AndroidView(
		factory = {ctx ->
			AdView(ctx).apply {
				adUnitId = "ca-app-pub-3940256099942544/6300978111"
				setAdSize(AdSize.BANNER)
			}
		},
		update = {ad ->
			ad.loadAd(AdRequest.Builder().build())
		},
		modifier = modifier
	)
}