package com.nhz.shared.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Prescription")
data class PrescriptionVO(
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0,
    var medicine : String = "",
    var quantity : Int = 0,
    var price : Float = 0f,
    var day : Int = 0,
    var morning : Int = 0,
    var evening : Int = 0,
    var night : Int = 0,
    var time : String = ""
)
