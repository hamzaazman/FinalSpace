package com.hamzaazman.finalspace.data.database

import androidx.room.*
import com.hamzaazman.finalspace.model.Character
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(value: List<Character>)

    @Query("SELECT * FROM character")
    fun getAll(): Flow<List<Character>>

    @Query("DELETE FROM character")
    suspend fun deleteAll()
}