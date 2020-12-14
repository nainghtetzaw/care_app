package com.nhz.shared.persistance.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nhz.shared.data.vos.MedicinesVO

@Dao
interface MedicinesDao {

    @Query("Select * From Medicines")
    fun getMedicines() : LiveData<List<MedicinesVO>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMedicines(medicines : List<MedicinesVO>)

}