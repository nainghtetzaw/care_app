package com.nhz.shared.persistance.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nhz.shared.data.vos.MedicalHistoryVO

@Dao
interface MedicalHistoryDao {

    @Query("SELECT * FROM Medical_History")
    fun getMedicalHistory() : LiveData<MedicalHistoryVO>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMedicalHistory(data : MedicalHistoryVO)
}