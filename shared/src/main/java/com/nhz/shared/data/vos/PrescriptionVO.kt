package com.nhz.shared.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Prescription")
data class PrescriptionVO(
    @PrimaryKey(autoGenerate = false)
    var medicine : String = "",
    var quantity : Int = 0,
    var price : Int = 0,
    var day : String = "",
    var morning : Boolean = false,
    var evening : Boolean = false,
    var night : Boolean = false,
    var note : String = "",
    var beforeOrAfter : Boolean = false
)
