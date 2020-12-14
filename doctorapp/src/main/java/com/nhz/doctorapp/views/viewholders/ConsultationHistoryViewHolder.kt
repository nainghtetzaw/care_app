package com.nhz.doctorapp.views.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.nhz.doctorapp.R
import com.nhz.shared.data.vos.ConsultationsVO

class ConsultationHistoryViewHolder(itemview : View) : BaseViewHolder<ConsultationsVO>(itemview) {

    private val ivBookedPatientProfile : ImageView = itemview.findViewById(R.id.ivBookedPatientProfile)
    private val tvBookedPatientName : TextView = itemview.findViewById(R.id.tvBookedPatientName)
    private val tvBookedPatientBd : TextView = itemview.findViewById(R.id.tvBookedPatientBd)

    override fun bindData(data: ConsultationsVO) {
        mData = data

        if (data.patient_info?.image != ""){
            Glide.with(itemView.context)
                .load(data.patient_info?.image)
                .into(ivBookedPatientProfile)
        }
        tvBookedPatientName.text = data.patient_info?.username
        tvBookedPatientBd.text = data.patient_info?.date_of_birth
    }
}