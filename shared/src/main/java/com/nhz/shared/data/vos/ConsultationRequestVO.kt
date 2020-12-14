package com.nhz.shared.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.nhz.shared.persistance.typeconverters.ConsultationPatientTypeConverter

@Entity(tableName = "Consultation Request")
@TypeConverters(ConsultationPatientTypeConverter::class)
data class ConsultationRequestVO(
    @PrimaryKey(autoGenerate = false)
    var id  : String = "",
    var patient : PatientVO ?= null,
    var specialityId : Int = 0,
    var available : Boolean = false,
    var old_or_new : Boolean = false
)
