package com.nhz.shared.persistance.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nhz.shared.data.vos.DoctorVO

@Dao
interface RecentDoctorDao {

    @Query("SELECT * FROM Doctor")
    fun getDoctorInfo() : LiveData<List<DoctorVO>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDoctor(doctor : List<DoctorVO>)

}