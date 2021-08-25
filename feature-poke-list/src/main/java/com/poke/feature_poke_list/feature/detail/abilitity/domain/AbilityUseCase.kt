package com.poke.feature_poke_list.feature.detail.abilitity.domain

import com.poke.core.data.repo.PokeRepository
import kotlinx.coroutines.flow.flow

class AbilityUseCase(private val repository: PokeRepository) {
    fun getPokeAbility(poke: Long) = flow {
        emit(repository.getAbilitiesFrom(poke))
    }
}