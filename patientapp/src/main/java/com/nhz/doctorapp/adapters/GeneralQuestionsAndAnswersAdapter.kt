package com.nhz.doctorapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.nhz.doctorapp.R
import com.nhz.doctorapp.views.viewholders.GeneralQuestionsAndAnswersViewholder
import com.nhz.shared.data.vos.CaseSummaryVO

class GeneralQuestionsAndAnswersAdapter : BaseAdapter<CaseSummaryVO,GeneralQuestionsAndAnswersViewholder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GeneralQuestionsAndAnswersViewholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_general_questions_and_answers,parent,false)
        return GeneralQuestionsAndAnswersViewholder(view)
    }
}