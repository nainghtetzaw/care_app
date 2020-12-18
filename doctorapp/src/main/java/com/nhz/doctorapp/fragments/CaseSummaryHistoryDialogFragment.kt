package com.nhz.doctorapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nhz.doctorapp.R
import com.nhz.doctorapp.adapters.GeneralInfoHistoryAdapter
import com.nhz.doctorapp.adapters.SpecialityInfoHistoryAdapter
import com.nhz.doctorapp.mvp.presenters.impls.CaseSummaryHistoryPresenterImpl
import com.nhz.doctorapp.mvp.presenters.interfaces.CaseSummaryHistoryPresenter
import com.nhz.doctorapp.mvp.views.CaseSummaryHistoryView
import com.nhz.doctorapp.views.viewholders.GeneralInfoHistoryViewHolder
import com.nhz.shared.data.vos.CaseSummaryVO

class CaseSummaryHistoryDialogFragment : DialogFragment(),CaseSummaryHistoryView {

    private var consultationId : String = ""
    private var patientId : String = ""
    private lateinit var rViewChatGeneralInfo : RecyclerView
    private lateinit var rViewChatSpecialityInfo : RecyclerView
    private lateinit var btnClose : Button

    private lateinit var mGeneralAdapter : GeneralInfoHistoryAdapter
    private lateinit var mSpecialityAdapter : SpecialityInfoHistoryAdapter
    private lateinit var mGeneralLayoutManager : LinearLayoutManager
    private lateinit var mSpecialityLayoutManager : LinearLayoutManager
    private lateinit var mPresenter : CaseSummaryHistoryPresenter

    companion object{

        const val BUNDLE_CONSULTATION_ID = "BUNDLE_CONSULTATION_ID"
        const val BUNDLE_PATIENT_ID = "BUNDLE_CONSULTATION_ID"
        const val TAG_CASE_SUMMARY_HISTORY = "TAG_CASE_SUMMARY_HISTORY"

        fun newInstance(consultationId : String,patientId : String) : CaseSummaryHistoryDialogFragment{
            return CaseSummaryHistoryDialogFragment().apply {
                val bundle = Bundle()
                bundle.putString(BUNDLE_CONSULTATION_ID,consultationId)
                bundle.putString(BUNDLE_PATIENT_ID,patientId)
                arguments = bundle
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            consultationId = it.getString(BUNDLE_CONSULTATION_ID,"")
            patientId = it.getString(BUNDLE_PATIENT_ID,"")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_case_summary_history_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rViewChatGeneralInfo = view.findViewById(R.id.rViewChatGeneralInfo)
        rViewChatSpecialityInfo = view.findViewById(R.id.rViewChatSpecialityInfo)
        btnClose = view.findViewById(R.id.btnClose)
        setPresenter()
        setUpRecyclerView()

        mPresenter.onUiReady(consultationId, patientId)
        btnClose.setOnClickListener {
            dismiss()
        }
    }

    override fun onResume() {
        super.onResume()
        val param = dialog?.window?.attributes?.apply {
            width = ViewGroup.LayoutParams.MATCH_PARENT
            height = ViewGroup.LayoutParams.WRAP_CONTENT
        }
        dialog?.window?.attributes = param
    }

    private fun setPresenter(){
        mPresenter = ViewModelProviders.of(this).get(CaseSummaryHistoryPresenterImpl::class.java)
        mPresenter.initPresenter(this)
    }

    private fun setUpRecyclerView(){
        activity?.let {
            mGeneralAdapter = GeneralInfoHistoryAdapter()
            mSpecialityAdapter = SpecialityInfoHistoryAdapter()
            mGeneralLayoutManager = LinearLayoutManager(it,LinearLayoutManager.VERTICAL,false)
            mSpecialityLayoutManager = LinearLayoutManager(it,LinearLayoutManager.VERTICAL,false)

            rViewChatGeneralInfo.adapter = mGeneralAdapter
            rViewChatGeneralInfo.layoutManager = mGeneralLayoutManager
            rViewChatSpecialityInfo.adapter = mSpecialityAdapter
            rViewChatSpecialityInfo.layoutManager = mSpecialityLayoutManager
        }
    }

    override fun showGeneralData(data: List<CaseSummaryVO>) {
        mGeneralAdapter.addNewData(data.toMutableList())
    }

    override fun showSpecialityData(data: List<CaseSummaryVO>) {
        mSpecialityAdapter.addNewData(data.toMutableList())
    }
}