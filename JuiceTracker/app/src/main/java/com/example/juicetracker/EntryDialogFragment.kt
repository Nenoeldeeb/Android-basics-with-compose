package com.example.juicetracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.navArgs
import com.example.juicetracker.data.JuiceColor
import com.example.juicetracker.databinding.FragmentEntryDialogBinding
import com.example.juicetracker.ui.AppViewModelProvider
import com.example.juicetracker.ui.EntryViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import android.R.layout
import android.widget.AdapterView
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

class EntryDialogFragment: BottomSheetDialogFragment() {
	
	private val entryViewModel by viewModels<EntryViewModel>{AppViewModelProvider.Factory}
	
	private var selectedColor: JuiceColor = JuiceColor.Red
	
	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		return FragmentEntryDialogBinding.inflate(inflater, container, false).root
	}
	
	/**
	 * Called immediately after [.onCreateView]
	 * has returned, but before any saved state has been restored in to the view.
	 * This gives subclasses a chance to initialize themselves once
	 * they know their view hierarchy has been completely created.  The fragment's
	 * view hierarchy is not however attached to its parent at this point.
	 * @param view The View returned by [.onCreateView].
	 * @param savedInstanceState If non-null, this fragment is being re-constructed
	 * from a previous saved state as given here.
	 */
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		
		val binding = FragmentEntryDialogBinding.bind(view)
		
		val colorsMap = JuiceColor.values().associateBy { getString(it.label) }
		val args: EntryDialogFragmentArgs by navArgs()
		val juiceId = args.itemId
		
		if (juiceId > 0) {
			viewLifecycleOwner.lifecycleScope.launch {
				repeatOnLifecycle(Lifecycle.State.STARTED) {
					entryViewModel.getJuiceStream(juiceId).filterNotNull().collect() { juice ->
						binding.name.setText(juice.name)
						binding.description.setText(juice.description)
						binding.ratingBar.rating = juice.rating.toFloat()
						binding.colorSpinner.setSelection(getColorIndex(juice.color))
					}
				}
			}
		}
		
		binding.colorSpinner.adapter = ArrayAdapter(
			requireContext(),
			layout.simple_spinner_item,
			colorsMap.map { it.key }
		)
		
		binding.name.doOnTextChanged {_, s, _, c ->
			binding.saveButton.isEnabled = (s + c) > 2
		}
		
		binding.colorSpinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
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
			selectedColor =
				colorsMap[parent?.getItemAtPosition(position).toString()]!!
			}
			
			/**
			 * Callback method to be invoked when the selection disappears from this
			 * view. The selection can disappear for instance when touch is activated
			 * or when the adapter becomes empty.
			 *
			 * @param parent The AdapterView that now contains no selected item.
			 */
			override fun onNothingSelected(parent: AdapterView<*>?) {
				selectedColor = JuiceColor.Red
			}
			
		}
		
		binding.saveButton.setOnClickListener {
			entryViewModel.saveJuice(
				juiceId,
				binding.name.text.toString(),
				binding.description.text.toString(),
				selectedColor.name,
				binding.ratingBar.rating.toInt()
			)
			dismiss()
		}
		
		binding.cancelButton.setOnClickListener {
			dismiss()
		}
	}
	
	private fun getColorIndex(color: String): Int {
		return JuiceColor.values().indexOf(
			JuiceColor.valueOf(color)
		)
	}
}