package com.nhz.doctorapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.nhz.doctorapp.R
import com.nhz.doctorapp.views.viewholders.ConsultationMedicineListViewHolder
import com.nhz.shared.data.vos.PrescriptionVO

class ConsultationMedicineListAdapter : BaseAdapter<PrescriptionVO,ConsultationMedicineListViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ConsultationMedicineListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_consultation_medicine_list,parent,false)
        return ConsultationMedicineListViewHolder(view)
    }
}