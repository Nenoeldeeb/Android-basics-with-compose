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
package com.example.juicetracker.ui

import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.juicetracker.data.Juice
import com.example.juicetracker.data.JuiceColor
import com.example.juicetracker.R
import com.google.accompanist.themeadapter.material3.Mdc3Theme

class JuiceListAdapter(
    private var onEdit: (Juice) -> Unit,
    private var onDelete: (Juice) -> Unit
) : ListAdapter<Juice, JuiceListAdapter.JuiceListViewHolder>(JuiceDiffCallback()) {
    
    class JuiceListViewHolder(
        private val composeView: ComposeView,
        private val onEdit: (Juice) -> Unit,
        private val onDelete: (Juice) -> Unit
    ) : RecyclerView.ViewHolder(composeView) {

        fun bind(juice: Juice) {
            composeView.setContent {
                JuiceListItem(
                    juice = juice,
                    onDelete = { onDelete(juice) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .clickable { onEdit(juice) }
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = JuiceListViewHolder(
        ComposeView(parent.context),
        onEdit,
        onDelete
    )

    override fun onBindViewHolder(holder: JuiceListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

@Composable
private fun JuiceListItem(
    juice: Juice,
    onDelete: (Juice) -> Unit,
    modifier: Modifier = Modifier
) {
    Mdc3Theme {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = modifier
        ) {
            JuiceIcon(
                color = juice.color,
            )
            JuiceItemDetails(
                juice = juice,
                modifier = Modifier.weight(1f)
            )
            DeleteButton(
                onDelete = { onDelete(juice) },
                modifier = Modifier.align(Alignment.Top)
            )
        }
    }
}

@Composable
private fun JuiceIcon(
    color: String,
    modifier: Modifier = Modifier
) {
    val colorsMap = JuiceColor.values().associateBy { stringResource(it.label) }
    val selectedColor = colorsMap[color]?.let { Color(it.color) }
    val contentDescription = stringResource(id = R.string.juice_color, color)
    
    Box(modifier = modifier.semantics { contentDescription }) {
        Icon(
            painter = painterResource(id = R.drawable.ic_juice_color),
            tint = selectedColor!!,
            contentDescription = null,
            modifier = Modifier.align(Alignment.Center)
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_juice_clear),
            contentDescription = null
        )
    }
}

@Composable
private fun JuiceRating(
    rating: Int,
    modifier: Modifier = Modifier
) {
    val contentDescription = pluralStringResource(id = R.plurals.number_of_stars, count = rating)
    Row(
        modifier = modifier.semantics { contentDescription }
    ) {
        repeat(rating) {
            Image(
                painter = painterResource(id = R.drawable.star),
                contentDescription = null,
                modifier = Modifier.size(32.dp)
            )
        }
    }
}

@Composable
private fun JuiceItemDetails(
    juice: Juice,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Top,
        modifier = modifier
    ) {
        Text(
            text = juice.name,
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
        )
        Text(juice.description)
        JuiceRating(
            rating = juice.rating,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

@Composable
private fun DeleteButton(
    onDelete: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = { onDelete() },
        modifier = modifier
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_delete),
            contentDescription = stringResource(id = R.string.delete),
            modifier = modifier
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun JuiceIconPreview() {
    JuiceIcon("Red")
}

@Composable
@Preview(showBackground = true)
private fun JuiceDetailsPreview() {
    JuiceItemDetails(
        Juice(
            id = 1,
            name = "Apple Juice",
            description = "A delicious juice made from fresh apples",
            color = "Red",
            rating = 4
        )
    )
}

@Composable
@Preview(showBackground = true)
private fun DeleteButtonPreview() {
    DeleteButton(onDelete = {})
}

@Composable
@Preview(showBackground = true)
private fun JuiceListItemPreview() {
    JuiceListItem(
        Juice(
            id = 1,
            name = "Apple Juice",
            description = "A delicious juice made from fresh apples",
            color = "Red",
            rating = 4
        ),
        onDelete = {}
    )
}

class JuiceDiffCallback : DiffUtil.ItemCallback<Juice>() {
    override fun areItemsTheSame(oldItem: Juice, newItem: Juice): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Juice, newItem: Juice): Boolean {
        return oldItem == newItem
    }
}
