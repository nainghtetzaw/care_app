package com.nhz.shared.persistance.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nhz.shared.data.vos.ConsultationsVO

@Dao
interface ConsultationDao {

    @Query("SELECT * FROM Consultations")
    fun getConsultations() : LiveData<List<ConsultationsVO>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertConsultation(consultation : List<ConsultationsVO>)

}