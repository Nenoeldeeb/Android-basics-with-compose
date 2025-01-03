package com.example.juicetracker.ui.bottomsheet

import android.widget.RatingBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.viewinterop.AndroidView
import com.example.juicetracker.R

@Composable
fun RatingInputRow(
	rating: Int,
	onRatingChanged: (Int) -> Unit,
	modifier: Modifier = Modifier
) {
	InputRow(inputLabel = stringResource(id = R.string.rating), modifier = modifier) {
		AndroidView(
			factory = {ctx ->
				RatingBar(ctx).apply {
					stepSize = 1f
				}
			},
			update = {r ->
				r.rating = rating.toFloat()
				r.setOnRatingBarChangeListener { _, rating, _ ->
					onRatingChanged(rating.toInt())
				}
			}
		)
	}
}