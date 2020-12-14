package com.nhz.shared.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Doctor")
data class DoctorVO(
    @PrimaryKey(autoGenerate = false)
    var userId : String = "",
    var username : String = "",
    var email : String = "",
    var name : String = "",
    var profileImage : String = "",
    var degree : String = "",
    var specialityId : Int = 0,
    var specialityType : String = "",
    var deviceId : String = ""
)
