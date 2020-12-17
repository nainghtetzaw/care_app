package com.nhz.doctorapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.nhz.doctorapp.R
import com.nhz.doctorapp.delegates.QuestionsDelegate
import com.nhz.doctorapp.views.viewholders.QuestionsViewHolder
import com.nhz.shared.data.vos.SpecialityQuestionsVO

class QuestionsAdapter(delegate : QuestionsDelegate) : BaseAdapter<SpecialityQuestionsVO,QuestionsViewHolder>() {

    private val mDelegate : QuestionsDelegate = delegate

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_speciality_question,parent,false)
        return QuestionsViewHolder(view,mDelegate)
    }
}