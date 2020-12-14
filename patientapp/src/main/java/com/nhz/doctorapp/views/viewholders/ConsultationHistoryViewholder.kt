package com.nhz.doctorapp.views.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.nhz.doctorapp.R
import com.nhz.shared.data.vos.ConsultationsVO

class ConsultationHistoryViewholder(itemview : View) : BaseViewHolder<ConsultationsVO>(itemview) {

    private val tvDoctorName : TextView = itemview.findViewById(R.id.tvDoctorName)
    private val tvDoctorSpeciality : TextView = itemview.findViewById(R.id.tvDoctorSpeciality)
    private val tvDoctorProfile : ImageView = itemview.findViewById(R.id.ivDoctorProfile)

    override fun bindData(data: ConsultationsVO) {
        mData = data

        Glide.with(itemView.context)
                .load(data.doctor_info?.profileImage)
                .into(tvDoctorProfile)

        tvDoctorName.text = data.doctor_info?.name
        tvDoctorSpeciality.text = data.doctor_info?.specialityType
    }
}