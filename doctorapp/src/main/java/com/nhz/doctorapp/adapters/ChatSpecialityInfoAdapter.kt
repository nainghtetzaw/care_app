package com.nhz.doctorapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.nhz.doctorapp.R
import com.nhz.doctorapp.views.viewholders.ChatSpecialityInfoViewHolder
import com.nhz.shared.data.vos.CaseSummaryVO

class ChatSpecialityInfoAdapter : BaseAdapter<CaseSummaryVO,ChatSpecialityInfoViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ChatSpecialityInfoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_patient_case_summary,parent,false)
        return ChatSpecialityInfoViewHolder(view)
    }
}