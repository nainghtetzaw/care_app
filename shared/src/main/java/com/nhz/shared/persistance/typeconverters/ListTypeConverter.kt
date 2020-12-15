package com.nhz.shared.persistance.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ListTypeConverter {

    @TypeConverter
    fun toString(data : List<String>) : String {
        return Gson().toJson(data)
    }

    @TypeConverter
    fun toList(dataStr  : String) : List<String>{
        val type = object : TypeToken<List<String>>(){}.type
        return Gson().fromJson(dataStr,type)
    }

}