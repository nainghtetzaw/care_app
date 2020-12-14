package com.nhz.shared.persistance.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nhz.shared.data.vos.PatientVO

class ConsultationPatientTypeConverter {

    @TypeConverter
    fun toString(data : PatientVO) : String {
        return  Gson().toJson(data)
    }

    @TypeConverter
    fun toObject(patient : String) : PatientVO{
        val dataType = object : TypeToken<PatientVO>() {}.type
        return Gson().fromJson(patient,dataType)
    }

}