package com.nhz.doctorapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.nhz.doctorapp.R
import com.nhz.doctorapp.views.viewholders.ChatGeneralInfoViewHolder
import com.nhz.shared.data.vos.CaseSummaryVO

class ChatGeneralInfoAdapter : BaseAdapter<CaseSummaryVO,ChatGeneralInfoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatGeneralInfoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_general_info,parent,false)
        return ChatGeneralInfoViewHolder(view)
    }
}