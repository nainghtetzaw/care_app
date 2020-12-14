package com.nhz.doctorapp.views.viewholders

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.doOnTextChanged
import com.nhz.doctorapp.R
import com.nhz.doctorapp.delegates.GeneralQuestionsDelegate
import com.nhz.shared.data.vos.GeneralQuestionsVO

class GeneralQuestionsViewholder(itemview : View,val mDelegate : GeneralQuestionsDelegate) : BaseViewHolder<GeneralQuestionsVO>(itemview) {

    private val etAnswer = itemview.findViewById<EditText>(R.id.etAnswer)
    private val tvQuestion = itemview.findViewById<TextView>(R.id.tvQuestion)

    override fun bindData(data: GeneralQuestionsVO) {
        mData = data
        tvQuestion.text = data.question
    }
}