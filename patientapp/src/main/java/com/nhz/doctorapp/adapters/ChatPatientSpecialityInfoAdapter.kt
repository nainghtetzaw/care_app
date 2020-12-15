package com.nhz.doctorapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.nhz.doctorapp.R
import com.nhz.doctorapp.views.viewholders.ChatPatientSpecialityInfoViewHolder
import com.nhz.shared.data.vos.CaseSummaryVO

class ChatPatientSpecialityInfoAdapter : BaseAdapter<CaseSummaryVO,ChatPatientSpecialityInfoViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatPatientSpecialityInfoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_speciality_info,parent,false)
        return ChatPatientSpecialityInfoViewHolder(view)
    }
}