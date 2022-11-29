package com.hamzaazman.finalspace.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hamzaazman.finalspace.model.Quote
import kotlinx.coroutines.flow.Flow

@Dao
interface QuoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(value: List<Quote>)

    @Query("SELECT * FROM quote")
    fun getAll(): Flow<List<Quote>>

    @Query("DELETE FROM quote")
    suspend fun deleteAll()
}