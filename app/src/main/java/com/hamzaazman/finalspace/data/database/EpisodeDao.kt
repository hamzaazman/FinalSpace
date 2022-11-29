package com.hamzaazman.finalspace.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hamzaazman.finalspace.model.Episode
import kotlinx.coroutines.flow.Flow

@Dao
interface EpisodeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(value: List<Episode>)

    @Query("SELECT * FROM episode")
    fun getAll(): Flow<List<Episode>>

    @Query("DELETE FROM episode")
    suspend fun deleteAll()
}