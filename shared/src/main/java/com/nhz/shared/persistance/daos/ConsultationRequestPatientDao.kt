package com.nhz.shared.persistance.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nhz.shared.data.vos.ConsultationRequestVO
import com.nhz.shared.data.vos.PatientVO

@Dao
interface ConsultationRequestPatientDao {

    @Query("SELECT * FROM `Consultation Request`")
    fun getRequestedPatientInfo() : LiveData<List<ConsultationRequestVO>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertInfoRequestedPatientInfo(requests : List<ConsultationRequestVO>)

}