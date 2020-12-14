package com.nhz.doctorapp.adapters

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.nhz.doctorapp.R
import com.nhz.doctorapp.views.viewholders.SpecialityQuestionsViewHolder
import com.nhz.shared.data.vos.CaseSummaryVO
import com.nhz.shared.data.vos.SpecialityQuestionsVO


class SpecialityQuestionsAdapter() : RecyclerView.Adapter<SpecialityQuestionsViewHolder>() {

    private var mData : MutableList<SpecialityQuestionsVO> = mutableListOf()
    private var mCaseSummary : HashMap<Int,CaseSummaryVO> = hashMapOf()
    private lateinit var etSpecialityAnswer : EditText

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpecialityQuestionsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_speciality_questions,parent,false)
        return SpecialityQuestionsViewHolder(view)
    }

    override fun onBindViewHolder(holder: SpecialityQuestionsViewHolder, position: Int) {
        etSpecialityAnswer = holder.itemView.findViewById(R.id.etSpecialityAnswer)
        holder.bindData(mData[position])
        etSpecialityAnswer.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                val value = mCaseSummary[position]
                if (value != null){
                    mCaseSummary[position] = CaseSummaryVO(
                            mData[position].id,
                            mData[position].question,
                            etSpecialityAnswer.text.toString())
                }
            }

        })
    }

    override fun getItemCount(): Int {
        return mData.count()
    }

    fun addNewData(data : MutableList<SpecialityQuestionsVO>){
        mData = data
        notifyDataSetChanged()
    }

    fun getCaseSummary() : HashMap<Int,CaseSummaryVO>{
        return mCaseSummary
    }

}