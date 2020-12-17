package com.nhz.doctorapp.views.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.nhz.doctorapp.R
import com.nhz.shared.data.vos.LiveChatVO

class SenderMessageViewHolder(itemview : View) : BaseViewHolder<LiveChatVO>(itemview) {

    private val tvMessage = itemview.findViewById<TextView>(R.id.tvMessage)
    private val ivMessageProfile = itemview.findViewById<ImageView>(R.id.ivMessageProfile)
    private val tvMessageDeliverTime = itemview.findViewById<TextView>(R.id.tvMessageReachedTime)

    override fun bindData(data: LiveChatVO) {
        mData = data

        if (data.sender_image != ""){
            Glide.with(itemView.context)
                    .load(data.sender_image)
                    .into(ivMessageProfile)
        }
        tvMessage.text = data.message
        tvMessageDeliverTime.text = data.time
    }
}