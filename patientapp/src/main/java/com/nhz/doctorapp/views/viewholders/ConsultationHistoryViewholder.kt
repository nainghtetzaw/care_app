package com.nhz.doctorapp.views.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.nhz.doctorapp.R
import com.nhz.doctorapp.delegates.ConsultationHistoryDelegate
import com.nhz.shared.data.vos.ConsultationsVO

class ConsultationHistoryViewholder(itemview : View,val mDelegate : ConsultationHistoryDelegate) : BaseViewHolder<ConsultationsVO>(itemview) {

    private val tvDoctorName : TextView = itemview.findViewById(R.id.tvDoctorName)
    private val tvDoctorSpeciality : TextView = itemview.findViewById(R.id.tvDoctorSpeciality)
    private val tvDoctorProfile : ImageView = itemview.findViewById(R.id.ivDoctorProfile)
    private val tvConsultationNote : TextView = itemview.findViewById(R.id.tvConsultationNote)
    private val tvConsultationMedicineList : TextView  = itemview.findViewById(R.id.tvConsultationMedicineList)
    private val tvConsultationDate : TextView = itemview.findViewById(R.id.tvConsultationDate)

    init {
        tvConsultationNote.setOnClickListener {
            mData?.let {
                mDelegate.onTapNote(it.id,it.patient_info?.username!!,it.patient_info?.date_of_birth!!)
            }
        }
        tvConsultationMedicineList.setOnClickListener {
            mData?.let {
                mDelegate.onTapPrescription(it.id)
            }
        }
    }

    override fun bindData(data: ConsultationsVO) {
        mData = data

        if (data.doctor_info?.profileImage != ""){
            Glide.with(itemView.context)
                    .load(data.doctor_info?.profileImage)
                    .into(tvDoctorProfile)
        }
        tvDoctorName.text = data.doctor_info?.name
        tvDoctorSpeciality.text = data.doctor_info?.specialityType
    }
}