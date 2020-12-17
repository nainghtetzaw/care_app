package com.nhz.doctorapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.nhz.doctorapp.R
import com.nhz.doctorapp.delegates.MedicineListDelegate
import com.nhz.doctorapp.views.viewholders.PrescriptionMedicineViewHolder
import com.nhz.shared.data.vos.MedicinesVO

class PrescriptionMedicineAdapter(delegate : MedicineListDelegate) : BaseAdapter<MedicinesVO,PrescriptionMedicineViewHolder>() {

    private val mDelegate : MedicineListDelegate = delegate

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PrescriptionMedicineViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_prescribe_medicine,parent,false)
        return PrescriptionMedicineViewHolder(view,mDelegate)
    }
}