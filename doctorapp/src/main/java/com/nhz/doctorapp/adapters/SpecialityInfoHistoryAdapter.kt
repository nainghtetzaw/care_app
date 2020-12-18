package com.nhz.doctorapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.nhz.doctorapp.R
import com.nhz.doctorapp.views.viewholders.SpecialityInfoHistoryViewHolder
import com.nhz.shared.data.vos.CaseSummaryVO

class SpecialityInfoHistoryAdapter : BaseAdapter<CaseSummaryVO,SpecialityInfoHistoryViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SpecialityInfoHistoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_speciality_patient_info,parent,false)
        return SpecialityInfoHistoryViewHolder(view)
    }
}