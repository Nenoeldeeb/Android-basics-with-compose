package com.example.juicetracker.ui.bottomsheet

import android.view.View
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.viewinterop.AndroidView
import com.example.juicetracker.R
import com.example.juicetracker.data.JuiceColor

class ColorSpinnerAdapter(
	val onColorChanged: (Int) -> Unit
): AdapterView.OnItemSelectedListener {
	
	/**
	 *
	 * Callback method to be invoked when an item in this view has been
	 * selected. This callback is invoked only when the newly selected
	 * position is different from the previously selected position or if
	 * there was no selected item.
	 *
	 * Implementers can call getItemAtPosition(position) if they need to access the
	 * data associated with the selected item.
	 *
	 * @param parent The AdapterView where the selection happened
	 * @param view The view within the AdapterView that was clicked
	 * @param position The position of the view in the adapter
	 * @param id The row id of the item that is selected
	 */
	override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
		onColorChanged(position)
	}
	
	/**
	 * Callback method to be invoked when the selection disappears from this
	 * view. The selection can disappear for instance when touch is activated
	 * or when the adapter becomes empty.
	 *
	 * @param parent The AdapterView that now contains no selected item.
	 */
	override fun onNothingSelected(parent: AdapterView<*>?) {
		onColorChanged(0)
	}
}

@Composable
fun ColorSpinnerRow(
	position: Int,
	onColorChanged: (Int) -> Unit,
	modifier: Modifier = Modifier
) {
	val colors = JuiceColor.values().map { stringResource(it.label) }.toTypedArray()
	
	InputRow(inputLabel = stringResource(id = R.string.color), modifier = modifier) {
		AndroidView(
			factory = { ctx ->
				Spinner(ctx).apply {
					adapter = ArrayAdapter(ctx, android.R.layout.simple_spinner_item, colors)
				}
			},
			update = { s ->
				s.setSelection(position)
				s.onItemSelectedListener = ColorSpinnerAdapter(onColorChanged)
			},
modifier = Modifier.fillMaxWidth()
			)
	}
}

internal fun findColorIndex(color: String): Int {
	return JuiceColor.values().indexOf(JuiceColor.valueOf(color))
}