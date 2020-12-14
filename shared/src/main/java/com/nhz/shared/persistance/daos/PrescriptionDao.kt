package com.nhz.shared.persistance.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nhz.shared.data.vos.PrescriptionVO

@Dao
interface PrescriptionDao {

    @Query("SELECT * FROM Prescription")
    fun getPrescription() : LiveData<List<PrescriptionVO>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertIntoPrescription(prescription : List<PrescriptionVO>)

}