package com.hamzaazman.finalspace.data.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    @TypeConverter
    fun resultToString(value: List<String>?): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun stringToResult(value: String): List<String>? {
        val listType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(value, listType)
    }
}