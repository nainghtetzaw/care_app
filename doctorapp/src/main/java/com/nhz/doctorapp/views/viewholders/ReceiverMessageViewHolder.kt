package com.nhz.doctorapp.views.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.nhz.doctorapp.R
import com.nhz.shared.data.vos.LiveChatVO

class ReceiverMessageViewHolder(itemview : View) : BaseViewHolder<LiveChatVO>(itemview) {

    private val tvOtherMessage : TextView = itemview.findViewById(R.id.tvOtherMessage)
    private val ivOtherMessageProfile : ImageView = itemview.findViewById(R.id.ivOtherMessageProfile)
    private val tvOtherMessageDeliveredTime : TextView = itemview.findViewById(R.id.tvOtherMessageDeliveredTime)

    override fun bindData(data: LiveChatVO) {
        mData = data
        if(data.receiver_image != ""){
            Glide.with(itemView.context)
                    .load(data.receiver_image)
                    .into(ivOtherMessageProfile)
        }
        tvOtherMessage.text = data.message
        tvOtherMessageDeliveredTime.text = data.timeStamp
    }
}