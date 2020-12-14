package com.nhz.doctorapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.nhz.doctorapp.R
import com.nhz.doctorapp.views.viewholders.PatientGeneralInfoViewHolder
import com.nhz.shared.data.vos.CaseSummaryVO

class PatientGeneralInfoAdapter : BaseAdapter<CaseSummaryVO,PatientGeneralInfoViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PatientGeneralInfoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_general_patient_info,parent,false)
        return PatientGeneralInfoViewHolder(view)
    }
}