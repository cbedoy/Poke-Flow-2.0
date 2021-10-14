package com.poke.feature_poke_list.feature.list.compose

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.poke.core.PokeViewModel2

@ExperimentalMaterialApi
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PokeList(viewModel: PokeViewModel2, listener: PokeItemOnClickListener) {
    val pokes = viewModel.state.collectAsState().value

    LazyVerticalGrid(
        cells = GridCells.Fixed(2)
    ) {
        items(pokes.size) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(8.dp)
            ) {
                PokeItem(poke = pokes[it], listener = listener)
            }
        }
    }
}
