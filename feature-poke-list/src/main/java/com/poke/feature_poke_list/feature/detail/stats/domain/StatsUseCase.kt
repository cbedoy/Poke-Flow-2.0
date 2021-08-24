package com.poke.feature_poke_list.feature.detail.stats.domain

import com.poke.core.data.repo.PokeRepository
import kotlinx.coroutines.flow.flow

class StatsUseCase (private val repository: PokeRepository) {
    fun getPokeStats(poke: Long) = flow {
        emit(repository.getStatsFrom(poke))
    }
}