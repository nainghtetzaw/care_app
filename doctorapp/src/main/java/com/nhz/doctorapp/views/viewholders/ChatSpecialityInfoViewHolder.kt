package com.nhz.doctorapp.views.viewholders

import android.view.View
import android.widget.TextView
import com.nhz.doctorapp.R
import com.nhz.shared.data.vos.CaseSummaryVO

class ChatSpecialityInfoViewHolder(itemview : View) : BaseViewHolder<CaseSummaryVO>(itemview) {

    private val tvSpecialityQuestion = itemview.findViewById<TextView>(R.id.tvSpecialityQuestion)
    private val tvSpecialityAnswer = itemview.findViewById<TextView>(R.id.tvSpecialityAnswer)

    override fun bindData(data: CaseSummaryVO) {
        mData = data
        tvSpecialityQuestion.text = data.question
        tvSpecialityAnswer.text = data.answer
    }
}