package com.nhz.doctorapp.views.viewholders

import android.view.View
import android.widget.TextView
import com.bumptech.glide.Glide
import com.nhz.doctorapp.R
import com.nhz.shared.data.vos.DoctorVO

class RecentDoctorViewholder(itemview : View) : BaseViewHolder<DoctorVO>(itemview) {
    override fun bindData(data: DoctorVO) {
        mData = data

        if (data.profileImage != ""){
            Glide.with(itemView.context)
                .load(data.profileImage)
                .into(itemView.findViewById(R.id.ivDoctorProfile))
        }
        itemView.findViewById<TextView>(R.id.tvDoctorName).text = data.name
        itemView.findViewById<TextView>(R.id.tvSpeciality).text = data.specialityType

    }
}