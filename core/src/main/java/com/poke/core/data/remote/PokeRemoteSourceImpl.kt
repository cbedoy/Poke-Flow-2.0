package com.poke.core.data.remote

import com.haroldadmin.cnradapter.NetworkResponse
import com.poke.core.PokeItem
import com.poke.core.PokeItemResponse
import com.poke.core.PokeResponse
import com.poke.core.data.service.PokeService
import com.poke.core.PokeSpecieResponse
import com.poke.core.data.database.dao.StatDao
import com.poke.core.data.database.model.Poke

class PokeRemoteSourceImpl(
    private val service: PokeService
) : PokeRemoteSource {

    override suspend fun requestPokeDetail(pokeIndex: Int) : PokeItemResponse? {
        return when(val response = service.getPokeDetail(pokeId = "$pokeIndex")) {
            is NetworkResponse.Success -> {
                response.body
            }
            else -> null
        }
    }

}