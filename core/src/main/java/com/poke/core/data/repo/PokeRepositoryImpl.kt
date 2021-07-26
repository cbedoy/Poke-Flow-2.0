package com.poke.core.data.repo

import androidx.paging.PagedList

import com.poke.core.data.pagination.PokeBoundaryCallback

class PokeRepositoryImpl(
    private val pagedListConfig: PagedList.Config,
    private val boundaryCallback: PokeBoundaryCallback
): PokeRepository {
}