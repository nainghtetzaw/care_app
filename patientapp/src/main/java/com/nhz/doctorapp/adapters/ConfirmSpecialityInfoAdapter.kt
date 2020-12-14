package com.nhz.doctorapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.nhz.doctorapp.R
import com.nhz.doctorapp.views.viewholders.ConfirmSpecialityInfoViewHolder
import com.nhz.shared.data.vos.CaseSummaryVO

class ConfirmSpecialityInfoAdapter : BaseAdapter<CaseSummaryVO,ConfirmSpecialityInfoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConfirmSpecialityInfoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_confirm_speciality_info,parent,false)
        return ConfirmSpecialityInfoViewHolder(view)
    }
}