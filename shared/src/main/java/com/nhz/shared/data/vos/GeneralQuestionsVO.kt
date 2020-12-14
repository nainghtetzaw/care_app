package com.nhz.shared.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "GeneralQuestions")
data class GeneralQuestionsVO(
    @PrimaryKey(autoGenerate = false)
    var id : Int = 0,
    var question : String = "",
    var one_time : Boolean = false
)
