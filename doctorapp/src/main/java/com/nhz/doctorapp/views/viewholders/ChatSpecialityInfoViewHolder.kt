package com.nhz.doctorapp.views.viewholders

import android.view.View
import android.widget.TextView
import com.nhz.doctorapp.R
import com.nhz.shared.data.vos.CaseSummaryVO

class ChatSpecialityInfoViewHolder(itemview : View) : BaseViewHolder<CaseSummaryVO>(itemview) {

    override fun bindData(data: CaseSummaryVO) {
        val tvQuestion = itemView.findViewById<TextView>(R.id.tvSpecialityQuestion)
        val tvSpecialityAnswer = itemView.findViewById<TextView>(R.id.tvSpecialityAnswer)
        mData = data
        tvQuestion.text = data.question
        tvSpecialityAnswer.text = data.answer
    }
}