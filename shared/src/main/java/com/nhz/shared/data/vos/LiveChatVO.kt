package com.nhz.shared.data.vos

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.firebase.Timestamp
import com.google.firebase.firestore.ServerTimestamp
import com.nhz.shared.persistance.typeconverters.DateTypeConverter
import com.nhz.shared.persistance.typeconverters.ListTypeConverter
import java.util.*

@Entity(tableName = "LiveChat")
@TypeConverters(ListTypeConverter::class)
data class LiveChatVO(
        @PrimaryKey(autoGenerate = true)
        var id: Int = 0,
        var message: String = "",
        var image: String = "",
        var sender_id: String = "",
        var sender_name: String = "",
        var sender_image: String = "",
        var receiver_id: String = "",
        var receiver_name: String = "",
        var receiver_image: String = "",
        var timeStamp: String = "",
        var time : String = "",
        var medicineList: List<String> ?= arrayListOf()
)
