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
import com.nhz.doctorapp.adapters.ConsultationMedicineListAdapter
import com.nhz.doctorapp.mvp.presenters.impls.PrescriptionHistoryPresenterImpl
import com.nhz.doctorapp.mvp.presenters.interfaces.PrescriptionHistoryPresenter
import com.nhz.doctorapp.mvp.views.PrescriptionHistoryView
import com.nhz.shared.data.vos.PrescriptionVO

class PrescriptionHistoryDialogFragment : DialogFragment(),PrescriptionHistoryView{

    private var consultationId : String = ""
    private lateinit var rViewConsultationMedicineList : RecyclerView
    private lateinit var btnClose : Button

    private lateinit var mAdapter : ConsultationMedicineListAdapter
    private lateinit var mLayoutManager: LinearLayoutManager
    private lateinit var mPresenter : PrescriptionHistoryPresenter

    companion object{

        const val CONSULTATION_ID = "CONSULTATION_ID"
        const val TAG_PRESCRIPTION_HISTORY = "TAG_PRESCRIPTION_HISTORY"
        fun newInstance(consultationId : String) : PrescriptionHistoryDialogFragment{
            return PrescriptionHistoryDialogFragment().apply {
                val bundle = Bundle()
                bundle.putString(CONSULTATION_ID,consultationId)
                arguments = bundle
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            consultationId = it.getString(CONSULTATION_ID,"")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rViewConsultationMedicineList = view.findViewById(R.id.rViewConsultationMedicineList)
        btnClose = view.findViewById(R.id.btnClose)
        setUpPresenter()
        setUpRecyclerView()
        mPresenter.onUiReady(consultationId)
        btnClose.setOnClickListener {
            dismiss()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_prescription_history_dialog, container, false)
    }

    override fun onResume() {
        super.onResume()
        val param = dialog?.window?.attributes?.apply {
            width = ViewGroup.LayoutParams.MATCH_PARENT
            height = ViewGroup.LayoutParams.WRAP_CONTENT
        }
        dialog?.window?.attributes = param
    }

    private fun setUpPresenter(){
        mPresenter = ViewModelProviders.of(this).get(PrescriptionHistoryPresenterImpl::class.java)
        mPresenter.initPresenter(this)
    }

    private fun setUpRecyclerView(){
        activity?.let {
            mAdapter = ConsultationMedicineListAdapter()
            mLayoutManager = LinearLayoutManager(it,LinearLayoutManager.VERTICAL,false)
            rViewConsultationMedicineList.adapter = mAdapter
            rViewConsultationMedicineList.layoutManager = mLayoutManager
        }
    }

    override fun showPrescriptionData(data: List<PrescriptionVO>) {
        mAdapter.addNewData(data.toMutableList())
    }

}