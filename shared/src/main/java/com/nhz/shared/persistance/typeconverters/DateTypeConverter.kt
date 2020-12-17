package com.nhz.shared.persistance.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.sql.Time
import java.util.*

class DateTypeConverter  {

    @TypeConverter
    fun toLong(date : Date) : String{
        return Gson().toJson(date)
    }

    @TypeConverter
    fun toDate(str : String) : Date{
        val type = object : TypeToken<Date>(){}.type
        return Gson().fromJson(str,type)
    }
}