package com.nhz.doctorapp.views.viewholders

import android.view.View
import android.widget.TextView
import com.nhz.doctorapp.R
import com.nhz.shared.data.vos.CaseSummaryVO

class PatientSpecialityInfoViewHolder(itemview : View) : BaseViewHolder<CaseSummaryVO>(itemview) {

    private val tvPatientSpecialityQuestion : TextView = itemview.findViewById(R.id.tvPatientSpecialityQuestion)
    private val tvPatientSpecialityAnswer : TextView = itemview.findViewById(R.id.tvPatientSpecialityAnswer)

    override fun bindData(data: CaseSummaryVO) {
        mData = data
        tvPatientSpecialityQuestion.text = data.question
        tvPatientSpecialityAnswer.text = data.answer
    }
}