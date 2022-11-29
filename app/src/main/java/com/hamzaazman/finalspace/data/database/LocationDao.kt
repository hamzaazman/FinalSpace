package com.hamzaazman.finalspace.data.database

import androidx.room.*
import com.hamzaazman.finalspace.model.Location
import kotlinx.coroutines.flow.Flow

@Dao
interface LocationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(value: List<Location>)

    @Query("SELECT * FROM location")
    fun getAll(): Flow<List<Location>>

    @Query("DELETE FROM Location")
    suspend fun deleteAll()
}