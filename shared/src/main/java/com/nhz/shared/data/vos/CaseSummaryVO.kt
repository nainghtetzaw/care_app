package com.nhz.shared.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CaseSummary")
data class CaseSummaryVO(
    @PrimaryKey(autoGenerate = false)
    var id : Int = 0,
    var question : String = "",
    var answer : String = "",
    var one_time : Boolean = false
)
