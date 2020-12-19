package com.nhz.doctorapp.views.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.nhz.doctorapp.R
import com.nhz.shared.data.vos.LiveChatVO

class ReceiverMessageViewHolder(itemview : View) : BaseViewHolder<LiveChatVO>(itemview) {

    private val ivOtherMessageProfile : ImageView = itemview.findViewById(R.id.ivOtherMessageProfile)
    private val tvOtherMessage : TextView = itemview.findViewById(R.id.tvOtherMessage)
    private val tvMessageDeliveredTime : TextView = itemview.findViewById(R.id.tvMessageSendTime)

    override fun bindData(data: LiveChatVO) {
        mData = data
        if (data.sender_image != ""){
            Glide.with(itemView.context)
                    .load(data.sender_image)
                    .into(ivOtherMessageProfile)
        }
        tvOtherMessage.text = data.message
        tvMessageDeliveredTime.text = data.time
    }
}