package com.nhz.shared.persistance.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nhz.shared.data.vos.CheckOutVO

@Dao
interface CheckOutDao {

    @Query("SELECT * FROM CheckOut")
    fun getCheckOut() : LiveData<CheckOutVO>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCheckOut(case : CheckOutVO)

}