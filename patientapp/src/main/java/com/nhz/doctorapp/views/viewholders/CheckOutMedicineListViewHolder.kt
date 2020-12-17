package com.nhz.doctorapp.views.viewholders

import android.view.View
import android.widget.TextView
import com.nhz.doctorapp.R
import com.nhz.shared.data.vos.PrescriptionVO

class CheckOutMedicineListViewHolder(itemview : View) : BaseViewHolder<PrescriptionVO>(itemview) {

    private val tvMedicineName : TextView = itemview.findViewById(R.id.tvMedicineName)
    private val tvMedicineAmount : TextView = itemview.findViewById(R.id.tvMedicineAmount)
    private val tvMedicinePrice : TextView = itemview.findViewById(R.id.tvMedicinePrice)

    override fun bindData(data: PrescriptionVO) {
        mData = data
        tvMedicineName.text = data.medicine
        tvMedicineAmount.text = data.quantity.toString()
        tvMedicinePrice.text = (data.quantity * data.price).toString()
    }
}