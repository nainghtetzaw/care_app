package com.nhz.doctorapp.views.viewholders

import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.nhz.doctorapp.R
import com.nhz.shared.data.vos.SpecialityQuestionsVO

class SpecialityQuestionsViewHolder(itemview : View) : BaseViewHolder<SpecialityQuestionsVO>(itemview) {

    private val tvSpecialityQuestion : TextView = itemview.findViewById(R.id.tvSpecialityQuestion)
    private val etSpecialityAnswer : EditText = itemview.findViewById(R.id.etSpecialityAnswer)

    override fun bindData(data: SpecialityQuestionsVO) {
        mData = data
        tvSpecialityQuestion.text = data.question
    }
}