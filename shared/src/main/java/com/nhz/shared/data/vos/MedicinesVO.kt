package com.nhz.shared.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Medicines")
data class MedicinesVO(
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0,
    var name : String = "",
    var price : Float = 0f,
    var quantity : Int = 0
)
