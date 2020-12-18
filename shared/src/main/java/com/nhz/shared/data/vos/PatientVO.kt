package com.nhz.shared.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Patient")
data class PatientVO(
    @PrimaryKey(autoGenerate = false)
    var userId : String = "",
    var username : String = "",
    var phoneNumber : Long = 0,
    var email : String = "",
    var date_of_birth : String = "",
    var deviceId : String = "",
    var image : String = ""
)
