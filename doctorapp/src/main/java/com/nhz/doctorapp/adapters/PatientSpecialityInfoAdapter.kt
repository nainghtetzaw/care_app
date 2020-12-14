package com.nhz.doctorapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.nhz.doctorapp.R
import com.nhz.doctorapp.views.viewholders.PatientSpecialityInfoViewHolder
import com.nhz.shared.data.vos.CaseSummaryVO

class PatientSpecialityInfoAdapter : BaseAdapter<CaseSummaryVO,PatientSpecialityInfoViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PatientSpecialityInfoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_speciality_patient_info,parent,false)
        return PatientSpecialityInfoViewHolder(view)
    }
}