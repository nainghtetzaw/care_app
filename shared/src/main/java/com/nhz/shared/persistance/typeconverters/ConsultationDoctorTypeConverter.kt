package com.nhz.shared.persistance.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nhz.shared.data.vos.DoctorVO

class ConsultationDoctorTypeConverter {

    @TypeConverter
    fun toString(data : DoctorVO) : String {
        return Gson().toJson(data)
    }

    @TypeConverter
    fun toObject(doctor : String) : DoctorVO{
        val dataType = object : TypeToken<DoctorVO>() {}.type
        return Gson().fromJson(doctor,dataType)
    }

}