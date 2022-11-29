package com.hamzaazman.finalspace.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "Episode")
data class Episode(
    @ColumnInfo(name = "air_date")
    val air_date: String?,

    @ColumnInfo(name = "characters")
    val characters: List<String>?,

    @ColumnInfo(name = "director")
    val director: String?,

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "img_url")
    val img_url: String?,

    @ColumnInfo(name = "name")
    val name: String?,

    @ColumnInfo(name = "writer")
    val writer: String?
) : Parcelable