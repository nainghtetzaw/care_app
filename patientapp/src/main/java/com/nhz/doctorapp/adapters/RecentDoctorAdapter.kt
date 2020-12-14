package com.nhz.doctorapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.nhz.doctorapp.R
import com.nhz.doctorapp.views.viewholders.RecentDoctorViewholder
import com.nhz.shared.data.vos.DoctorVO

class RecentDoctorAdapter : BaseAdapter<DoctorVO,RecentDoctorViewholder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentDoctorViewholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recent_doctor,parent,false)
        return RecentDoctorViewholder(view)
    }
}