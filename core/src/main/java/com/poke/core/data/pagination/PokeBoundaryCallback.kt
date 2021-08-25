package com.poke.core.data.pagination

import androidx.paging.PagedList
import com.poke.core.PokeItem
import com.poke.core.PokeItemResponse
import com.poke.core.PokeSpecieResponse
import com.poke.core.data.database.dao.MoveDao
import com.poke.core.data.database.model.Poke
import com.poke.core.data.database.dao.PokeDao
import com.poke.core.data.database.dao.StatDao
import com.poke.core.data.database.model.Move
import com.poke.core.data.database.model.Stat
import com.poke.core.data.remote.PokeRemoteSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class PokeBoundaryCallback(
    private val pokeRemoteSource: PokeRemoteSource,
    private val pokeLocalStorage: PokeLocalStorage,
    private val coroutineScope: CoroutineScope
) : PagedList.BoundaryCallback<Poke>() {

    override fun onZeroItemsLoaded() {
        handleLoadItems()
    }

    private fun handleLoadItems() {
        coroutineScope.launch {
            val pokeResponse = pokeRemoteSource.requestPokeDetail(pokeLocalStorage.getPokeCount + 1)
            pokeResponse?.let {
                pokeLocalStorage.savePoke(it)
            }
        }
    }

    override fun onItemAtEndLoaded(itemAtEnd: Poke) {
        handleLoadItems()
    }

    private val PokeSpecieResponse.description : String
        get() = flavorTextEntries.firstOrNull { it.flavorText != null }?.flavorText?.removeBreadlines ?: ""

    private val PokeItem.id: String?
        get() = url.split("/").lastOrNull{ it.isNotEmpty() }


    private val PokeItemResponse.abilitiesAsText: String
        get() = abilities?.joinToString(
            separator = "\n",
            transform = {
                "- ${it.ability?.name?:""}"
            }) ?: ""

    private val String.removeBreadlines
        get() = replace("\n", "")
}