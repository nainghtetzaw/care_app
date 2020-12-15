package com.nhz.doctorapp.views.viewholders

import android.view.View
import android.widget.TextView
import com.nhz.doctorapp.R
import com.nhz.shared.data.vos.CaseSummaryVO

class ChatGeneralInfoViewHolder(itemview : View) : BaseViewHolder<CaseSummaryVO>(itemview) {

    private val tvPatientGeneralQuestion = itemview.findViewById<TextView>(R.id.tvPatientGeneralQuestion)
    private val tvPatientGeneralAnswer = itemview.findViewById<TextView>(R.id.tvPatientGeneralAnswer)

    override fun bindData(data: CaseSummaryVO) {
        mData = data
        tvPatientGeneralQuestion.text = data.question
        tvPatientGeneralAnswer.text = data.answer
    }
}