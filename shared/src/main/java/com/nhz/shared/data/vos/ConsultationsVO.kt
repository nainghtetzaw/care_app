package com.nhz.shared.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.nhz.shared.persistance.typeconverters.ConsultationDoctorTypeConverter
import com.nhz.shared.persistance.typeconverters.ConsultationPatientTypeConverter

@Entity(tableName = "Consultations")
@TypeConverters(ConsultationDoctorTypeConverter::class,ConsultationPatientTypeConverter::class)
data class ConsultationsVO(
    @PrimaryKey(autoGenerate = false)
    var id: String = "",
    var doctorId : String = "",
    var patientId : String = "",
    var doctor_info: DoctorVO? = null,
    var patient_info: PatientVO? = null,
    var finished: Boolean = false,
    var accept : Boolean = false
)
