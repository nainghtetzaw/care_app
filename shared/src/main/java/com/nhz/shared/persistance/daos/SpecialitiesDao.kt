package com.nhz.shared.persistance.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nhz.shared.data.vos.SpecialitiesVO

@Dao
interface SpecialitiesDao {

    @Query("SELECT * FROM Specialities")
    fun getSpecialities() : LiveData<List<SpecialitiesVO>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertIntoSpecialities(specialities : List<SpecialitiesVO>)

}