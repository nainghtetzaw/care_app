package com.nhz.shared.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Medical_History")
data class MedicalHistoryVO(
        @PrimaryKey(autoGenerate = true)
        val id : Int = 0,
        val date_of_consultation : String = "",
        val note : String = ""
)
