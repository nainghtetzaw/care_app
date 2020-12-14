package com.nhz.shared.persistance.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nhz.shared.data.vos.GeneralQuestionsVO

@Dao
interface GeneralQuestionsDao {

    @Query("SELECT * FROM GeneralQuestions")
    fun getGeneralQuestions() : LiveData<List<GeneralQuestionsVO>>

    @Query("SELECT * FROM GeneralQuestions  WHERE one_time = :oneTime")
    fun getGeneralQuestionsByBoolean(oneTime : Boolean) : LiveData<List<GeneralQuestionsVO>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGeneralQuestions(questions : List<GeneralQuestionsVO>)

}