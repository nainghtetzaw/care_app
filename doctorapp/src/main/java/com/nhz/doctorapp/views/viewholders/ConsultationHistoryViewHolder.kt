package com.nhz.doctorapp.views.viewholders

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.nhz.doctorapp.R
import com.nhz.doctorapp.delegates.HomeDelegate
import com.nhz.shared.data.vos.ConsultationsVO

class ConsultationHistoryViewHolder(itemview : View,val mDelegate : HomeDelegate) : BaseViewHolder<ConsultationsVO>(itemview) {

    private val ivBookedPatientProfile : ImageView = itemview.findViewById(R.id.ivBookedPatientProfile)
    private val tvBookedPatientName : TextView = itemview.findViewById(R.id.tvBookedPatientName)
    private val tvBookedPatientBd : TextView = itemview.findViewById(R.id.tvBookedPatientBd)
    private val tvBookedMedication : TextView = itemview.findViewById(R.id.tvBookedMedication)
    private val tvBookedPrescription : TextView = itemview.findViewById(R.id.tvBookedPrescription)
    private val tvBookedNote : TextView = itemview.findViewById(R.id.tvBookedNote)
    private val btnMessageBookedPatient : Button = itemview.findViewById(R.id.btnMessageBookedPatient)

    init {
        tvBookedMedication.setOnClickListener {
            mData?.let {
                mDelegate.onTapMedication(it.id,it.patientId)
            }
        }
        tvBookedPrescription.setOnClickListener {
            mData?.let {
                mDelegate.onTapPrescription(it.id)
            }
        }
        tvBookedNote.setOnClickListener {
            mData?.let {
                mDelegate.onTapNote(it.id,it.patient_info?.username!!,it.patient_info?.date_of_birth!!)
            }
        }
        btnMessageBookedPatient.setOnClickListener {
            mData?.let {
                mDelegate.onTapMessage(it.patient_info?.username!!,it.patientId,it.patient_info?.date_of_birth!!,it.patient_info?.image!!,it.id)
            }
        }
    }

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