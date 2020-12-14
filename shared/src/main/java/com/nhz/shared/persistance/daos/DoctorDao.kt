package com.nhz.shared.persistance.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nhz.shared.data.vos.DoctorVO

@Dao
interface DoctorDao {

    @Query("SELECT * FROM Doctor")
    fun getDoctorInfo() : LiveData<DoctorVO>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDoctor(doctor : DoctorVO)

}