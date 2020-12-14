package com.nhz.doctorapp.views.viewholders

import android.view.View
import android.widget.TextView
import com.nhz.doctorapp.R
import com.nhz.shared.data.vos.CaseSummaryVO

class ConfirmGeneralQuestionsAndAnswersViewHolder(itemview : View) : BaseViewHolder<CaseSummaryVO>(itemview) {

    private val tvQuestion : TextView = itemview.findViewById(R.id.tvQuestion)
    private val tvAnswer : TextView = itemview.findViewById(R.id.tvAnswer)

    override fun bindData(data: CaseSummaryVO) {
        mData = data

        tvQuestion.text = data.question
        tvAnswer.text = data.answer
    }
}