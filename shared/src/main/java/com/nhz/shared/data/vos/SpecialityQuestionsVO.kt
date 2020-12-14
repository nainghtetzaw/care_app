package com.nhz.shared.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "SpecialityQuestions")
data class SpecialityQuestionsVO(
    @PrimaryKey(autoGenerate = false)
    var id : Int = 0,
    var question : String = ""
)
