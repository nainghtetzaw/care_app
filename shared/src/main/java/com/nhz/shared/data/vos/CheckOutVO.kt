package com.nhz.shared.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.nhz.shared.persistance.typeconverters.ConsultationPatientTypeConverter

@Entity(tableName = "CheckOut")
@TypeConverters(ConsultationPatientTypeConverter::class)
data class CheckOutVO(
        @PrimaryKey(autoGenerate = true)
        var id: Int = 0,
        var patient: PatientVO?= null,
        var address: String = "",
        var delivery_date: String = "",
        var total_price: Int = 0,
        var total_quantity: Int = 0
)
