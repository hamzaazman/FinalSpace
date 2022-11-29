package com.hamzaazman.finalspace.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Quote")
data class Quote(

    @ColumnInfo(name = "by")
    val by: String?,

    @ColumnInfo(name = "character")
    val character: String?,

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "image")
    val image: String?,

    @ColumnInfo(name = "quote")
    val quote: String?
)