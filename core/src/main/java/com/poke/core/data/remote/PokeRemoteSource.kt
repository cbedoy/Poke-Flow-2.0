package com.poke.core.data.remote

import com.poke.core.data.database.model.Poke

interface PokeRemoteSource {

    suspend fun requestPokeDetail(pokeIndex: Int): Poke?

}