package com.nhz.doctorapp.views.viewholders

import android.view.View
import android.widget.TextView
import com.nhz.doctorapp.R
import com.nhz.doctorapp.delegates.QuestionsDelegate
import com.nhz.shared.data.vos.SpecialityQuestionsVO

class QuestionsViewHolder(itemview : View,mDelegate : QuestionsDelegate) : BaseViewHolder<SpecialityQuestionsVO>(itemview) {

    private val tvQuestion : TextView = itemview.findViewById(R.id.tvQuestion)

    init {
        itemview.setOnClickListener {
            mData?.let {
                mDelegate.onTapItem(it.question,itemview.context)
            }
        }
    }

    override fun bindData(data: SpecialityQuestionsVO) {
        mData = data
        tvQuestion.text = data.question
    }
}