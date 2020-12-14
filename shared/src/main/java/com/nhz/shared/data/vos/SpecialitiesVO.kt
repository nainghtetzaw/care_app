package com.nhz.shared.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Specialities")
data class SpecialitiesVO(
    @PrimaryKey(autoGenerate = false)
    var id : Int = 0,
    var name : String = "",
    var icon : String = "",
)
