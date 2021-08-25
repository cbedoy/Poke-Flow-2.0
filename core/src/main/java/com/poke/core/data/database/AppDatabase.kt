package com.poke.core.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.poke.core.data.database.dao.AbilityDao
import com.poke.core.data.database.dao.MoveDao
import com.poke.core.data.database.model.Poke
import com.poke.core.data.database.dao.PokeDao
import com.poke.core.data.database.dao.StatDao
import com.poke.core.data.database.model.Ability
import com.poke.core.data.database.model.Move
import com.poke.core.data.database.model.Stat

@Database(entities = [Poke::class, Stat::class, Move::class, Ability::class], version = 5)
abstract class AppDatabase : RoomDatabase() {
    abstract fun pokeDao(): PokeDao

    abstract fun statDao(): StatDao

    abstract fun moveDao(): MoveDao

    abstract fun abilityDao(): AbilityDao
}