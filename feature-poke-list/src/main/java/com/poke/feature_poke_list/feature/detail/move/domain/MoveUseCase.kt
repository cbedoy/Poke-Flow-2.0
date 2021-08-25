package com.poke.feature_poke_list.feature.detail.move.domain

import com.poke.core.data.repo.PokeRepository
import kotlinx.coroutines.flow.flow

class MoveUseCase(private val repository: PokeRepository) {
    fun getMovePoke(poke: Long) = flow {
        emit(repository.getMovesFrom(poke))
    }
}