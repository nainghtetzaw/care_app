package com.nhz.doctorapp.views.viewholders

import android.view.View
import android.widget.TextView
import com.nhz.doctorapp.R
import com.nhz.shared.data.vos.CaseSummaryVO

class ChatPatientGeneralInfoViewHolder(itemview : View) : BaseViewHolder<CaseSummaryVO>(itemview) {

    private val tvCaseSummaryQuestions : TextView = itemview.findViewById(R.id.tvCaseSummaryQuestion)
    private val tvCaseSummaryAnswer : TextView = itemview.findViewById(R.id.tvCaseSummaryAnswer)

    override fun bindData(data: CaseSummaryVO) {
        mData = data
        tvCaseSummaryQuestions.text = data.question
        tvCaseSummaryAnswer.text = data.answer
    }
}