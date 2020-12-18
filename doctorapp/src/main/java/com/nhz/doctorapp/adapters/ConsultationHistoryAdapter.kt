package com.nhz.doctorapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.nhz.doctorapp.R
import com.nhz.doctorapp.delegates.HomeDelegate
import com.nhz.doctorapp.views.viewholders.ConsultationHistoryViewHolder
import com.nhz.shared.data.vos.ConsultationsVO

class ConsultationHistoryAdapter(delegate : HomeDelegate) : BaseAdapter<ConsultationsVO,ConsultationHistoryViewHolder>() {

    private val mDelegate : HomeDelegate = delegate

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ConsultationHistoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_booking_history,parent,false)
        return ConsultationHistoryViewHolder(view,mDelegate)
    }
}