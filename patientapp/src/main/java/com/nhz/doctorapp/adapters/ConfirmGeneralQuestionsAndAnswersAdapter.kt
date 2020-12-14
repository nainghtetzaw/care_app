package com.nhz.doctorapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.nhz.doctorapp.R
import com.nhz.doctorapp.views.viewholders.ConfirmGeneralQuestionsAndAnswersViewHolder
import com.nhz.shared.data.vos.CaseSummaryVO

class ConfirmGeneralQuestionsAndAnswersAdapter : BaseAdapter<CaseSummaryVO,ConfirmGeneralQuestionsAndAnswersViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConfirmGeneralQuestionsAndAnswersViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_general_questions_and_answers,parent,false)
        return ConfirmGeneralQuestionsAndAnswersViewHolder(view)
    }
}