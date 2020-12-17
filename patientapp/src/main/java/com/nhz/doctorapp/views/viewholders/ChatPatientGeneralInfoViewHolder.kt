package com.nhz.doctorapp.views.viewholders

import android.view.View
import android.widget.TextView
import com.nhz.doctorapp.R
import com.nhz.shared.data.vos.CaseSummaryVO

class ChatPatientGeneralInfoViewHolder(itemview : View) : BaseViewHolder<CaseSummaryVO>(itemview) {

    private val tvCaseSummaryQuestions : TextView = itemview.findViewById(R.id.tvMedicineName)
    private val tvCaseSummaryAnswer : TextView = itemview.findViewById(R.id.tvMedicinePrice)

    override fun bindData(data: CaseSummaryVO) {
        mData = data
        tvCaseSummaryQuestions.text = data.question
        tvCaseSummaryAnswer.text = data.answer
    }
}