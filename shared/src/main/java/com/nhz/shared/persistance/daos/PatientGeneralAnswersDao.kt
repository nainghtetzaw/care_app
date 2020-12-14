package com.nhz.shared.persistance.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nhz.shared.data.vos.CaseSummaryVO

@Dao
interface PatientGeneralAnswersDao {

    @Query("SELECT * From CaseSummary")
    fun getAllCaseSummary() : LiveData<List<CaseSummaryVO>>

    @Query("SELECT * FROM CaseSummary WHERE one_time = :onetime ")
    fun getCaseSummaryByBoolean(onetime : Boolean) : LiveData<List<CaseSummaryVO>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCaseSummaryData(case : List<CaseSummaryVO>)

}