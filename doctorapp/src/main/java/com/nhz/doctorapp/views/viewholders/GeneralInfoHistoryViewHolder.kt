package com.nhz.doctorapp.views.viewholders

import android.view.View
import android.widget.TextView
import com.nhz.doctorapp.R
import com.nhz.shared.data.vos.CaseSummaryVO

class GeneralInfoHistoryViewHolder(itemview : View) : BaseViewHolder<CaseSummaryVO>(itemview) {

    private val tvPatientGeneralQuestion : TextView = itemview.findViewById(R.id.tvPatientGeneralQuestion)
    private val tvPatientGeneralAnswer  : TextView = itemview.findViewById(R.id.tvPatientGeneralAnswer)

    override fun bindData(data: CaseSummaryVO) {
        mData = data
        tvPatientGeneralQuestion.text = data.question
        tvPatientGeneralAnswer.text = data.answer
    }
}