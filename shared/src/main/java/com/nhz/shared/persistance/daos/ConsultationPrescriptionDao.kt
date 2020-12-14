package com.nhz.shared.persistance.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nhz.shared.data.vos.PrescriptionVO

@Dao
interface ConsultationPrescriptionDao {

    @Query("Select * From Prescription")
    fun getAllPrescription() : LiveData<List<PrescriptionVO>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertIntoConsultationPrescription(prescription : List<PrescriptionVO>)

}