package com.poke.core.data.local

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.poke.core.data.database.dao.PokeDao
import com.poke.core.data.database.model.Poke
import com.poke.core.data.pagination.PokeBoundaryCallback

class LocalSourceImpl(
    private val dao: PokeDao,
    private val pagedListConfig: PagedList.Config,
    private val boundaryCallback: PokeBoundaryCallback
): LocalSource{

    override val pokeLiveData: LiveData<PagedList<Poke>>
        get() = LivePagedListBuilder(
            dao.getAllAsDataSource(),
            pagedListConfig
        ).setBoundaryCallback(boundaryCallback).build()
}