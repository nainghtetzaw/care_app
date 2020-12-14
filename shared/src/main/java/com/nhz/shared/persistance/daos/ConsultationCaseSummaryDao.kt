package com.nhz.shared.persistance.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nhz.shared.data.vos.CaseSummaryVO

@Dao
interface ConsultationCaseSummaryDao {

    @Query("SELECT * From CaseSummary")
    fun getAllCaseSummary() : LiveData<List<CaseSummaryVO>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCaseSummaryData(case : List<CaseSummaryVO>)

}