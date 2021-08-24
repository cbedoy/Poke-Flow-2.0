package com.poke.core.data.repo

import com.poke.core.data.database.model.Move
import com.poke.core.data.database.model.Stat

interface PokeRepository {
    suspend fun getStatsFrom(poke: Long): List<Stat>

    suspend fun getMovesFrom(poke: Long): List<Move>
}