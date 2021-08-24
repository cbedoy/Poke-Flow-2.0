package com.poke.core.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.poke.core.data.database.model.Move

@Dao
interface MoveDao {
    @Query("SELECT * FROM Move WHERE poke == :pokeId")
    suspend fun getAll(pokeId: Long): List<Move>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(moves: List<Move>)
}