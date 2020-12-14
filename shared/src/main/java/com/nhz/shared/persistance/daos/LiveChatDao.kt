package com.nhz.shared.persistance.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nhz.shared.data.vos.LiveChatVO

@Dao
interface LiveChatDao {

    @Query("SELECT * FROM LiveChat")
    fun getChat() : LiveData<List<LiveChatVO>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertChat(chat : List<LiveChatVO>)

}