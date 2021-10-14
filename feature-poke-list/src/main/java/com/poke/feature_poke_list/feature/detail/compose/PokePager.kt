package com.poke.feature_poke_list.feature.detail.compose


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.poke.core.data.database.model.Poke
import com.poke.feature_poke_list.feature.detail.abilitity.presentation.AbilityViewModel
import com.poke.feature_poke_list.feature.detail.move.presentation.MoveViewModel
import com.poke.feature_poke_list.feature.style.CommonMargin
import com.poke.feature_poke_list.feature.style.MediumPadding
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

private const val PAGES = 3
private const val PAGE_MOVE = 0
private const val PAGE_ABILITY = 1
private const val PAGE_STAT = 2

@OptIn(ExperimentalPagerApi::class)
@Composable
fun PokePager(poke: Poke) {
    HorizontalPager(count = PAGES) { page ->
        // Our page content
        if (page == PAGE_MOVE) {
            PokePagerMoveList(poke)
        } else if (page == PAGE_ABILITY) {
            PokePagerAbilityList(poke)
        }
    }
}

@Composable
fun PokePagerMoveList(poke: Poke) {
    val viewModel = getViewModel<MoveViewModel>( parameters = {
        parametersOf(poke.number)
    })

    TitleAndListContainer(
        title = "Moves",
        dataSource = viewModel.state.collectAsState().value
    ) { item ->
        PokeMoveView(move = item.name)
    }
}

@Composable
fun PokePagerAbilityList(poke: Poke) {
    val viewModel = getViewModel<AbilityViewModel>( parameters = {
        parametersOf(poke.number)
    })

    TitleAndListContainer(
        title = "Abilities",
        dataSource = viewModel.state.collectAsState().value
    ) { item ->
        PokeAbilityView(ability = item.name)
    }
}

@Composable
fun <T> TitleAndListContainer(title: String, dataSource: List<T>, itemContent: @Composable (value: T) -> Unit){
    Column(Modifier.padding(MediumPadding)) {
        Text(text = title, fontSize = 32.sp)
        Spacer(modifier = Modifier.height(8.dp))
        LazyColumn {
            items(dataSource.size) { index ->
                itemContent(dataSource[index])
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true, backgroundColor = 0xffffff)
@Composable
fun PokePagerPreview() {
    PokePager(
        Poke(number = 0, name = "Charmander", type = "water,fire")
    )
}