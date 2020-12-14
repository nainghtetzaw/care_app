package com.nhz.doctorapp.views.viewholders

import android.view.View
import com.nhz.shared.data.vos.LiveChatVO

class ReceiverMessageList(itemview : View) : BaseViewHolder<LiveChatVO>(itemview) {
    override fun bindData(data: LiveChatVO) {
        mData = data
    }
}