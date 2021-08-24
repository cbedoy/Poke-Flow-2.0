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
    private val pokeDao: PokeDao,
    private val statDao: StatDao,
    private val moveDao: MoveDao,
    private val coroutineScope: CoroutineScope
) : PagedList.BoundaryCallback<Poke>() {

    private val currentSize
        get() = pokeDao.getAll().size

    override fun onZeroItemsLoaded() {
        handleLoadItems()
    }

    private fun handleLoadItems() {
        coroutineScope.launch {
            val pokeResponse = pokeRemoteSource.requestPokeDetail(currentSize + 1)
            pokeResponse?.let {
                val poke =  preparePokeWith(pokeResponse)
                val stats = prepareStatWith(pokeResponse)
                val moves = prepareMoveWith(pokeResponse)
                poke.let {
                    pokeDao.insert(poke)
                }
                moveDao.insertAll(moves)
                statDao.insertAll(stats)
            }
        }
    }

    override fun onItemAtEndLoaded(itemAtEnd: Poke) {
        handleLoadItems()
    }

    private fun prepareMoveWith(
        pokeItemResponse: PokeItemResponse
    ) : List<Move> {
        return with(pokeItemResponse) {
            moves?.map {
                Move(poke = id?:0, name = it.move?.name?:"")
            }?: emptyList()
        }
    }

    private fun prepareStatWith(
        pokeItemResponse: PokeItemResponse
    ) : List<Stat> {
        return with(pokeItemResponse) {
            stats?.map {
                Stat(poke = id?:0, name = it.stat?.name?:"", value = it.baseStat?:0)
            }?: emptyList()
        }
    }

    private fun preparePokeWith(
        detail: PokeItemResponse
    ) : Poke {
        return with(detail){
            Poke(
                number = id?:0,
                name = name?.capitalize()?:"",
                image = sprites?.avatar?:"",
                type = typesAsText,
                moves = movesAsText,
                abilities = abilitiesAsText,
                color = ""
            )
        }
    }

    private val PokeSpecieResponse.description : String
        get() = flavorTextEntries.firstOrNull { it.flavorText != null }?.flavorText?.removeBreadlines ?: ""

    private val PokeItem.id: String?
        get() = url.split("/").lastOrNull{ it.isNotEmpty() }


    private val PokeItemResponse.typesAsText: String
        get() = types?.joinToString(separator = ",", transform = {
            it.type?.name?:""
        }) ?: ""

    private val PokeItemResponse.movesAsText: String
        get() = moves?.joinToString(
            separator = "\n",
            transform = {
                "- ${it.move?.name?:""}"
            }) ?: ""

    private val PokeItemResponse.abilitiesAsText: String
        get() = abilities?.joinToString(
            separator = "\n",
            transform = {
                "- ${it.ability?.name?:""}"
            }) ?: ""

    private val String.removeBreadlines
        get() = replace("\n", "")
}