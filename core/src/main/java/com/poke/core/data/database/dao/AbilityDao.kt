package com.poke.core.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.poke.core.data.database.model.Ability

@Dao
interface AbilityDao {
    @Query("SELECT * FROM Ability WHERE poke == :pokeId")
    suspend fun getAll(pokeId: Long): List<Ability>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(abilities: List<Ability>)
}