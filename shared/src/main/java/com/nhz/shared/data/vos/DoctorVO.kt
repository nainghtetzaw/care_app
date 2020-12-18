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
    var phone : Long = 0,
    var experience : Int = 0,
    var description : String = "",
    var profileImage : String = "",
    var degree : String = "",
    var gender : Boolean = false,
    var date_of_birth : String = "",
    var specialityId : Int = 0,
    var specialityType : String = "",
    var deviceId : String = ""
)
