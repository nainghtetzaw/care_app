package com.nhz.shared.persistance.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nhz.shared.data.vos.CaseSummaryVO

@Dao
interface RequestedPatientCaseSummaryDao {

    @Query("SELECT * FROM CaseSummary")
    fun getRequestedPatientCaseSummary() : LiveData<List<CaseSummaryVO>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertIntoRequestedPatientCaseSummary(case : List<CaseSummaryVO>)

}