package com.poke.core.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.poke.core.data.database.model.Stat

@Dao
interface StatDao {
    @Query("SELECT * FROM Stat WHERE poke == :pokeId")
    suspend fun getAll(pokeId: Long): List<Stat>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(stats: List<Stat>)
}