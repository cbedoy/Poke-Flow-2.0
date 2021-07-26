package com.poke.core.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.poke.core.data.database.model.Poke
import com.poke.core.data.database.dao.PokeDao

@Database(entities = [Poke::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun pokeDao(): PokeDao
}