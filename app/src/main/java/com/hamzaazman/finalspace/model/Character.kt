package com.hamzaazman.finalspace.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "Character")
data class Character(
    @ColumnInfo(name = "gender")
    val gender: String?,

    @ColumnInfo(name = "hair")
    val hair: String?,

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "img_url")
    val img_url: String?,

    @ColumnInfo(name = "name")
    val name: String?,

    @ColumnInfo(name = "origin")
    val origin: String?,

    @ColumnInfo(name = "species")
    val species: String? = null,

    @ColumnInfo(name = "status")
    val status: String?
) : Parcelable