package com.nhz.doctorapp.adapters

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.nhz.doctorapp.R
import com.nhz.doctorapp.delegates.GeneralQuestionsDelegate
import com.nhz.doctorapp.mvp.views.GeneralQuestionsView
import com.nhz.doctorapp.views.viewholders.GeneralQuestionsViewholder
import com.nhz.shared.data.vos.CaseSummaryVO
import com.nhz.shared.data.vos.GeneralQuestionsVO

class GeneralQuestionsAdapter(delegate : GeneralQuestionsDelegate) : RecyclerView.Adapter<GeneralQuestionsViewholder>() {

    private val mDelegate : GeneralQuestionsDelegate = delegate
    private var mData : MutableList<GeneralQuestionsVO> = mutableListOf()
    private var mCaseSummary : MutableList<CaseSummaryVO> = mutableListOf()
    private lateinit var etAnswer  : EditText

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GeneralQuestionsViewholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_general_questions_form,parent,false)
        return GeneralQuestionsViewholder(view,mDelegate)
    }

    override fun onBindViewHolder(holder: GeneralQuestionsViewholder, position: Int) {
        etAnswer = holder.itemView.findViewById(R.id.etAnswer)
        holder.bindData(mData[position])
        holder.itemView.findViewById<EditText>(R.id.etAnswer).addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                mCaseSummary.add(CaseSummaryVO(mData[position].id,mData[position].question,s.toString(),mData[position].one_time))
            }

        })
    }

    override fun getItemCount(): Int {
        return mData.count()
    }

    fun addNewData(data : MutableList<GeneralQuestionsVO>){
        mData = data
        notifyDataSetChanged()
    }

    fun getGeneralData() : List<CaseSummaryVO>{
        return mCaseSummary
    }
}