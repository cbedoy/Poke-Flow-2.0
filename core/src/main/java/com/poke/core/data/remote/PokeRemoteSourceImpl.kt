package com.poke.core.data.remote

import com.haroldadmin.cnradapter.NetworkResponse
import com.poke.core.PokeItem
import com.poke.core.PokeItemResponse
import com.poke.core.data.service.PokeService
import com.poke.core.PokeSpecieResponse
import com.poke.core.data.database.model.Poke

class PokeRemoteSourceImpl(private val service: PokeService) : PokeRemoteSource {

    override suspend fun requestPokeDetail(pokeIndex: Int) : Poke? {
        return when(val response = service.getPokeDetail(pokeId = "$pokeIndex")) {
            is NetworkResponse.Success -> {
                preparePokeWith(
                    response.body
                )
            }
            else -> null
        }
    }


    private fun preparePokeWith(
        detail: PokeItemResponse
    ) : Poke {
        return with(detail){
            Poke(
                number = id?:0,
                name = name?.capitalize()?:"",
                image = sprites?.avatar?:"",
                type = typesAsText,
                moves = movesAsText,
                abilities = abilitiesAsText,
                color = ""
            )
        }
    }

    private val PokeSpecieResponse.description : String
        get() = flavorTextEntries.firstOrNull { it.flavorText != null }?.flavorText?.removeBreadlines ?: ""

    private val PokeItem.id: String?
        get() = url.split("/").lastOrNull{ it.isNotEmpty() }


    private val PokeItemResponse.typesAsText: String
        get() = types?.joinToString(separator = ",", transform = {
            it.type?.name?:""
        }) ?: ""

    val PokeItemResponse.movesAsText: String
        get() = moves?.joinToString(
            separator = "\n",
            transform = {
                "- ${it.move?.name?:""}"
            }) ?: ""

    val PokeItemResponse.abilitiesAsText: String
        get() = abilities?.joinToString(
            separator = "\n",
            transform = {
                "- ${it.ability?.name?:""}"
            }) ?: ""

    val String.removeBreadlines
        get() = replace("\n", "")
}