package com.hamzaazman.finalspace.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.hamzaazman.finalspace.model.Character
import com.hamzaazman.finalspace.model.Episode
import com.hamzaazman.finalspace.model.Location
import com.hamzaazman.finalspace.model.Quote

@Database(
    entities = [
        Character::class,
        Episode::class,
        Location::class,
        Quote::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
    abstract fun episodeDao(): EpisodeDao
    abstract fun locationDao(): LocationDao
    abstract fun quoteDao(): QuoteDao
}