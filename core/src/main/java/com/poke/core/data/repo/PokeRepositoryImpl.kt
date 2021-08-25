package com.poke.core.data.repo

import com.poke.core.data.database.dao.AbilityDao
import com.poke.core.data.database.dao.MoveDao
import com.poke.core.data.database.dao.StatDao
import com.poke.core.data.database.model.Ability
import com.poke.core.data.database.model.Move
import com.poke.core.data.database.model.Stat

class PokeRepositoryImpl(
    private val statDao: StatDao,
    private val moveDao: MoveDao,
    private val abilityDao: AbilityDao
) : PokeRepository {
    override suspend fun getStatsFrom(poke: Long): List<Stat> {
        return statDao.getAll(pokeId = poke)
    }

    override suspend fun getMovesFrom(poke: Long): List<Move> {
        return moveDao.getAll(pokeId = poke)
    }

    override suspend fun getAbilitiesFrom(poke: Long): List<Ability> {
        return abilityDao.getAll(pokeId = poke)
    }
}