package com.nhz.doctorapp.views.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.nhz.doctorapp.R
import com.nhz.doctorapp.delegates.MedicineListDelegate
import com.nhz.shared.data.vos.MedicinesVO

class PrescriptionMedicineViewHolder(itemview : View,val mDelegate : MedicineListDelegate) : BaseViewHolder<MedicinesVO>(itemview) {

    private val tvPrescribeMedicine : TextView = itemview.findViewById(R.id.tvPrescribeMedicine)
    private val ivAddMedicine : ImageView = itemview.findViewById(R.id.ivAddMedicine)
    private var isClick  :Boolean = false

    init {
        ivAddMedicine.setOnClickListener {
            if (!isClick){
                mData?.let {medicine ->
                    mDelegate.onAddMedicine(medicine.name,medicine.price)
                    ivAddMedicine.setImageResource(R.drawable.ic_baseline_horizontal_rule_24)
                    ivAddMedicine.background = ContextCompat.getDrawable(itemview.context,R.color.red)
                    isClick = true
                }
            }else{
                mData?.let { medicine ->
                    mDelegate.onDeleteMedicine(medicine.name)
                    ivAddMedicine.setImageResource(R.drawable.ic_baseline_add_24)
                    ivAddMedicine.background = ContextCompat.getDrawable(itemview.context,R.color.grey)
                    isClick = false
                }
            }
        }
    }

    override fun bindData(data: MedicinesVO) {
        mData = data
        tvPrescribeMedicine.text = data.name
    }
}