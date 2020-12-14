package com.nhz.shared.data.vos

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "LiveChat")
data class LiveChatVO(
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0,
    var message : String = "",
    var image : String = "",
    var sender_id : String = "",
    var sender_name : String = "",
    var sender_image : String = "",
    var receiver_id : String = "",
    var receiver_name : String = "",
    var receiver_image : String = "",
    var timeStamp : Int = 0
)
