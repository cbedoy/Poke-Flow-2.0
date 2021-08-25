package com.poke.core.data.pagination

import com.poke.core.PokeItemResponse
import com.poke.core.PokeResponse
import com.poke.core.data.database.dao.AbilityDao
import com.poke.core.data.database.dao.MoveDao
import com.poke.core.data.database.dao.PokeDao
import com.poke.core.data.database.dao.StatDao
import com.poke.core.data.database.model.Ability
import com.poke.core.data.database.model.Move
import com.poke.core.data.database.model.Poke
import com.poke.core.data.database.model.Stat
import com.poke.core.extensions.normalize

class PokeLocalStorageImpl (
    private val pokeDao: PokeDao,
    private val moveDao: MoveDao,
    private val statDao: StatDao,
    private val abilityDao: AbilityDao
    ): PokeLocalStorage{

    override val getPokeCount: Int
        get() = pokeDao.getAll().size

    override fun savePoke(response: PokeItemResponse) {
        val poke =  preparePokeWith(response)
        val stats = prepareStatWith(response)
        val moves = prepareMoveWith(response)
        val abilities = prepareAbilitiesWith(response)
        poke.let {
            pokeDao.insert(poke)
        }
        moveDao.insertAll(moves)
        statDao.insertAll(stats)
        abilityDao.insertAll(abilities)
    }

    private fun prepareAbilitiesWith(pokeItemResponse: PokeItemResponse): List<Ability> {
        return with(pokeItemResponse) {
            abilities?.map {
                Ability(poke = id?:0, name = it.ability?.name.normalize)
            }?: emptyList()
        }
    }

    private fun prepareMoveWith(
        pokeItemResponse: PokeItemResponse
    ) : List<Move> {
        return with(pokeItemResponse) {
            moves?.map {
                Move(poke = id?:0, name = it.move?.name.normalize)
            }?: emptyList()
        }
    }

    private fun prepareStatWith(
        pokeItemResponse: PokeItemResponse
    ) : List<Stat> {
        return with(pokeItemResponse) {
            stats?.map {
                Stat(poke = id?:0, name = it.stat?.name?.normalize?:"", value = it.baseStat?:0)
            }?: emptyList()
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
                color = ""
            )
        }
    }

    private val PokeItemResponse.typesAsText: String
        get() = types?.joinToString(separator = ",", transform = {
            it.type?.name?:""
        }) ?: ""

}