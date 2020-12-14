package com.nhz.shared.persistance.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nhz.shared.data.vos.PatientVO

@Dao
interface PatientDao {

    @Query("Select * From Patient")
    fun getPatientInfo() : LiveData<PatientVO>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPatientInfo(patient : PatientVO)

}