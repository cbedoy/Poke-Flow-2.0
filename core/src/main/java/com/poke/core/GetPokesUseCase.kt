package com.poke.core

import com.haroldadmin.cnradapter.NetworkResponse
import com.poke.core.data.pagination.PokeLocalStorage
import com.poke.core.data.service.PokeService
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlin.coroutines.CoroutineContext

class GetPokesUseCase(
    private val service: PokeService,
    private val pokeLocalStorage: PokeLocalStorage,
    private val ioCoroutineScope: CoroutineContext
) {

    operator fun invoke(pokeCount: Int) = flow {
        var currentPokeCount = pokeLocalStorage.currentPokes.run {
            emit(this)
            size
        }

        while (pokeCount < currentPokeCount) {
            service.getPokeDetail("${currentPokeCount+1}").let { networkResponse ->
                when(networkResponse) {
                    is NetworkResponse.Success -> {
                        pokeLocalStorage.savePoke(networkResponse.body)

                        emit(pokeLocalStorage.currentPokes)

                        currentPokeCount++
                    }
                    else -> {

                    }
                }
            }
        }
    }.flowOn(ioCoroutineScope)
}