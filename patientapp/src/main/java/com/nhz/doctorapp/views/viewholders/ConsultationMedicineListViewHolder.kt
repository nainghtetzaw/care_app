package com.nhz.doctorapp.views.viewholders

import android.view.View
import android.widget.TextView
import com.nhz.doctorapp.R
import com.nhz.shared.data.vos.PrescriptionVO

class ConsultationMedicineListViewHolder(itemview : View) : BaseViewHolder<PrescriptionVO>(itemview) {

    private val tvQuantity : TextView = itemView.findViewById(R.id.tvQuantity)
    private val tvRoutine : TextView = itemview.findViewById(R.id.tvRoutine)
    private val tvDay : TextView = itemview.findViewById(R.id.tvDay)
    private val tvNote : TextView = itemView.findViewById(R.id.tvNote)
    private val tvConsultationMedicineName : TextView = itemview.findViewById(R.id.tvConsultationMedicineName)

    override fun bindData(data: PrescriptionVO) {
        mData = data
        tvConsultationMedicineName.text = data.medicine
        tvQuantity.text = data.quantity.toString()
        tvDay.text = data.day

        if (data.morning && data.evening && data.night){
            tvRoutine.text = "Morning/Noon/Evening"
        }else if (data.morning && data.evening && !data.night){
            tvRoutine.text = "Morning/Noon"
        }else if (data.morning && !data.evening && !data.night){
            tvRoutine.text = "Morning"
        }else if (!data.morning && data.evening && data.night){
            tvRoutine.text = "Noon/Evening"
        }else if (!data.morning && data.evening && !data.night){
            tvRoutine.text = "Noon"
        }else if (!data.morning && !data.evening && data.night){
            tvRoutine.text = "Evening"
        }else {
            tvRoutine.text = "Morning/Evening"
        }

        if (data.beforeOrAfter){
            tvNote.text = "Take it before meal"
        }else {
            tvNote.text = "Take it after meal"
        }
    }
}