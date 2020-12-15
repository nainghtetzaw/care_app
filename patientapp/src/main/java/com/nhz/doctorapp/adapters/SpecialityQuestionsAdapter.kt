package com.nhz.doctorapp.adapters

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.nhz.doctorapp.R
import com.nhz.doctorapp.views.viewholders.SpecialityQuestionsViewHolder
import com.nhz.shared.data.vos.CaseSummaryVO
import com.nhz.shared.data.vos.SpecialityQuestionsVO


class SpecialityQuestionsAdapter() : RecyclerView.Adapter<SpecialityQuestionsViewHolder>() {

    private var mData : MutableList<SpecialityQuestionsVO> = mutableListOf()
    private var mCaseSummary : MutableList<CaseSummaryVO> = mutableListOf()
    private lateinit var etSpecialityAnswer : EditText

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpecialityQuestionsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_speciality_questions,parent,false)
        return SpecialityQuestionsViewHolder(view)
    }

    override fun onBindViewHolder(holder: SpecialityQuestionsViewHolder, position: Int) {
        etSpecialityAnswer = holder.itemView.findViewById(R.id.etSpecialityAnswer)
        holder.bindData(mData[position])
        etSpecialityAnswer.doOnTextChanged { text, start, before, count ->
            mCaseSummary.add(CaseSummaryVO(mData[position].id,mData[position].question,text.toString()))
        }
//        etSpecialityAnswer.doOnTextChanged { text, start, before, count ->
//            val caseSummary = CaseSummaryVO(mData[position].id,mData[position].question,text.toString())
//            mCaseSummary.find { it.id == caseSummary.id }?.let {
//                mCaseSummary.remove(it)
//                if (caseSummary.answer.isNotEmpty()){
//                    mCaseSummary.add(it.copy(answer = caseSummary.answer))
//                }
//            } ?: mCaseSummary.add(caseSummary)
//        }
    }

    override fun getItemCount(): Int {
        return mData.count()
    }

    fun addNewData(data : MutableList<SpecialityQuestionsVO>){
        mData = data
        notifyDataSetChanged()
    }

    fun getCaseSummary() : List<CaseSummaryVO>{
        return mCaseSummary
    }

}