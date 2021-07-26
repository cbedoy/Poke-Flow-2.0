package com.poke.core.data.pagination

import androidx.paging.PagedList
import com.poke.core.data.database.model.Poke
import com.poke.core.data.database.dao.PokeDao
import com.poke.core.data.remote.PokeRemoteSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class PokeBoundaryCallback(
    private val remoteSource: PokeRemoteSource,
    private val dao: PokeDao,
    private val coroutineScope: CoroutineScope
) : PagedList.BoundaryCallback<Poke>() {

    private val currentSize
        get() = dao.getAll().size

    override fun onZeroItemsLoaded() {
        handleLoadItems()
    }

    private fun handleLoadItems() {
        coroutineScope.launch {
            val poke = remoteSource.requestPokeDetail(currentSize + 1)
            poke?.let {
                dao.insert(poke)
            }
        }
    }

    override fun onItemAtEndLoaded(itemAtEnd: Poke) {
        handleLoadItems()
    }


}