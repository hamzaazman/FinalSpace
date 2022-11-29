package com.hamzaazman.finalspace.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "Location")
data class Location(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "img_url")
    val img_url: String?,

    @ColumnInfo(name = "name")
    val name: String?,

    @ColumnInfo(name = "notable_residents")
    val notable_residents: List<String>?,

    @ColumnInfo(name = "type")
    val type: String?
) : Parcelable