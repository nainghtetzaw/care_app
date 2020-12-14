package com.nhz.shared.persistance.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nhz.shared.data.vos.SpecialityQuestionsVO

@Dao
interface SpecialityQuestionsDao {

    @Query("SELECT * FROM SpecialityQuestions")
    fun getSpecialityQuestions() : LiveData<List<SpecialityQuestionsVO>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertIntoSpecialityQuestions(questions : List<SpecialityQuestionsVO>)

}