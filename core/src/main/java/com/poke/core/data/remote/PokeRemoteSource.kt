package com.poke.core.data.remote

import com.poke.core.PokeItemResponse

interface PokeRemoteSource {

    suspend fun requestPokeDetail(pokeIndex: Int): PokeItemResponse?

}