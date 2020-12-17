package com.nhz.doctorapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.nhz.doctorapp.R
import com.nhz.doctorapp.views.viewholders.OrderMedicineViewHolder
import com.nhz.shared.data.vos.PrescriptionVO

class OrderMedicineAdapter : BaseAdapter<PrescriptionVO,OrderMedicineViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderMedicineViewHolder {
        val view  = LayoutInflater.from(parent.context).inflate(R.layout.item_order_medicine_list,parent,false)
        return OrderMedicineViewHolder(view)
    }
}