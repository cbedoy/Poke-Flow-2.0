package com.poke.core.data.database.dao

import androidx.paging.DataSource
import androidx.room.*
import com.poke.core.data.database.model.Poke

@Dao
interface PokeDao {
    @Query("SELECT * FROM poke ORDER BY number ASC")
    fun getAll(): List<Poke>

    @Query("SELECT * FROM poke ORDER BY number ASC")
    fun getAllAsDataSource(): DataSource.Factory<Int, Poke>

    @Query("SELECT * FROM poke WHERE name LIKE :queryString ORDER BY number ASC")
    fun getAllAsDataSourceWithQuery(queryString: String): DataSource.Factory<Int, Poke>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(pokes: List<Poke>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(poke: Poke)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(poke: Poke)
}