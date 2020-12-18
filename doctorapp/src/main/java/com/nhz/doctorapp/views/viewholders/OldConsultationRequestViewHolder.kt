package com.nhz.doctorapp.views.viewholders

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.nhz.doctorapp.R
import com.nhz.doctorapp.delegates.HomeDelegate
import com.nhz.shared.data.vos.ConsultationRequestVO

class OldConsultationRequestViewHolder(itemview : View,val mDelegate : HomeDelegate) : BaseViewHolder<ConsultationRequestVO>(itemview) {

    private val ivRequestedPatient : ImageView = itemview.findViewById(R.id.ivRequestedPatient)
    private val tvRequestPatientName : TextView = itemview.findViewById(R.id.tvRequestedPatientName)
    private val tvRequestPatientBd : TextView = itemview.findViewById(R.id.tvRequestedPatientBd)
    private val btnAcceptRequest : Button = itemview.findViewById(R.id.btnAcceptRequest)
    private val btnLaterRequest : Button = itemview.findViewById(R.id.btnLaterRequest)
    private val btnSelectedTime : Button = itemview.findViewById(R.id.btnSelectTime)

    init {
        btnAcceptRequest.setOnClickListener {
            mData?.let { request ->
                mDelegate.onTapAcceptRequest(request)
            }
        }
        btnLaterRequest.setOnClickListener {
            mData?.let {
                it.patient?.userId?.let { it1 -> mDelegate.onTapRemoveRequest(it1) }
            }
        }
        btnSelectedTime.setOnClickListener {
            mDelegate.onTapSelectTime()
        }
    }

    override fun bindData(data: ConsultationRequestVO) {
        mData = data

        if (data.patient?.image != ""){
            Glide.with(itemView.context)
                .load(data.patient?.image)
                .into(ivRequestedPatient)
        }
        tvRequestPatientBd.text = data.patient?.date_of_birth
        tvRequestPatientName.text = data.patient?.username
    }


}