package com.nhz.doctorapp.views.viewholders

import android.view.View
import android.widget.TextView
import com.nhz.doctorapp.R
import com.nhz.shared.data.vos.CaseSummaryVO

class ConfirmSpecialityInfoViewHolder(itemview : View) : BaseViewHolder<CaseSummaryVO>(itemview) {

    private val tvConfirmSpecialityQuestion : TextView = itemview.findViewById(R.id.tvConfirmSpecialityQuestion)
    private val tvConfirmSpecialityAnswer : TextView = itemview.findViewById(R.id.tvConfirmSpecialityAnswer)

    override fun bindData(data: CaseSummaryVO) {
        mData = data

        tvConfirmSpecialityQuestion.text = data.question
        tvConfirmSpecialityAnswer.text = data.answer
    }
}