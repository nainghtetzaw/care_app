package com.nhz.doctorapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nhz.doctorapp.R
import com.nhz.doctorapp.adapters.ConsultationHistoryAdapter

class ConsultationHistoryFragment : Fragment() {

    private lateinit var rViewConsultationList : RecyclerView
    private lateinit var btnConsultationBack : Button
    private lateinit var mLinearLayoutManager: LinearLayoutManager
    private lateinit var mAdapter : ConsultationHistoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_consultation_history_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        onClickEventListeners()
    }

    private fun setUpRecyclerView(){
        activity?.let {
            rViewConsultationList = it.findViewById(R.id.rViewConsultationList)

            mLinearLayoutManager = LinearLayoutManager(it,LinearLayoutManager.VERTICAL,false)
            mAdapter = ConsultationHistoryAdapter()

            rViewConsultationList.layoutManager = mLinearLayoutManager
            rViewConsultationList.adapter = mAdapter
        }
    }

    private fun onClickEventListeners(){
        activity?.let {

        }
    }
}