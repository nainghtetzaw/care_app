package com.nhz.doctorapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.nhz.doctorapp.R
import com.nhz.doctorapp.views.viewholders.ChatPatientGeneralInfoViewHolder
import com.nhz.shared.data.vos.CaseSummaryVO

class ChatPatientGeneralInfoAdapter: BaseAdapter<CaseSummaryVO,ChatPatientGeneralInfoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatPatientGeneralInfoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_general_info,parent,false)
        return ChatPatientGeneralInfoViewHolder(view)
    }
}