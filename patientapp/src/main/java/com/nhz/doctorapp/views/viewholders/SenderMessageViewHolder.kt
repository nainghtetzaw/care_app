package com.nhz.doctorapp.views.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.nhz.doctorapp.R
import com.nhz.shared.data.vos.LiveChatVO

class SenderMessageViewHolder(itemview  : View):BaseViewHolder<LiveChatVO>(itemview) {

    private val tvMessage : TextView = itemview.findViewById(R.id.tvMessage)
    private val tvMessageDeliveredTime : TextView = itemview.findViewById(R.id.tvMessageReachedTime)
    private val ivMessageProfile : ImageView = itemview.findViewById(R.id.ivMessageProfile)

    override fun bindData(data: LiveChatVO) {
        mData = data
        if(data.sender_image != ""){
            Glide.with(itemView.context)
                    .load(data.sender_image)
                    .into(ivMessageProfile)
        }
        tvMessage.text = data.message
        tvMessageDeliveredTime.text = data.timeStamp
    }
}