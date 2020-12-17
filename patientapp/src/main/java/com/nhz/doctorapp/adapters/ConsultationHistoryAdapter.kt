package com.nhz.doctorapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.nhz.doctorapp.R
import com.nhz.doctorapp.delegates.ConsultationHistoryDelegate
import com.nhz.doctorapp.views.viewholders.ConsultationHistoryViewholder
import com.nhz.shared.data.vos.ConsultationsVO

class ConsultationHistoryAdapter(delegate : ConsultationHistoryDelegate) : BaseAdapter<ConsultationsVO,ConsultationHistoryViewholder>() {

    private val mDelegate : ConsultationHistoryDelegate = delegate

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConsultationHistoryViewholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_consultation,parent,false)
        return ConsultationHistoryViewholder(view,mDelegate)
    }
}