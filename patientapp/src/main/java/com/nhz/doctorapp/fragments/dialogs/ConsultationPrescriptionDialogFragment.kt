package com.nhz.doctorapp.fragments.dialogs

import android.os.Bundle
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
import com.nhz.doctorapp.mvp.presenters.ConsultationPrescriptionPresenter
import com.nhz.doctorapp.mvp.presenters.impls.ConsultationPrescriptionPresenterImpl
import com.nhz.doctorapp.mvp.views.ConsultationPrescriptionView
import com.nhz.shared.data.vos.PrescriptionVO


class ConsultationPrescriptionDialogFragment : DialogFragment(),ConsultationPrescriptionView {

    private var mConsultationId : String = ""
    private lateinit var rViewConsultationMedicineList : RecyclerView
    private lateinit var btnClose : Button

    private lateinit var mAdapter : ConsultationMedicineListAdapter
    private lateinit var mLayoutManager : LinearLayoutManager
    private lateinit var mPresenter : ConsultationPrescriptionPresenter

    companion object{

        const val TAG_CONSULTATION_PRESCRIPTION = "TAG_CONSULTATION_PRESCRIPTION"
        const val BUNDLE_CONSULTATION_ID = "BUNDLE_CONSULTATION_ID"

        fun newInstance(consultationId : String) : ConsultationPrescriptionDialogFragment{
            val bundle = Bundle()
            bundle.putString(BUNDLE_CONSULTATION_ID,consultationId)
            return ConsultationPrescriptionDialogFragment().apply {
                arguments = bundle
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.fragment_consultation_prescription_dialog,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rViewConsultationMedicineList = view.findViewById(R.id.rViewConsultationMedicineList)
        btnClose = view.findViewById(R.id.btnClose)
        setUpPresenter()
        setUpRecyclerView()

        mPresenter.onUiReady(mConsultationId)

        btnClose.setOnClickListener {
            dismiss()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mConsultationId = it.getString(BUNDLE_CONSULTATION_ID,"")
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

    private fun setUpPresenter(){
        mPresenter = ViewModelProviders.of(this).get(ConsultationPrescriptionPresenterImpl::class.java)
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

    override fun showPrescriptionList(data: List<PrescriptionVO>) {
        mAdapter.addNewData(data.toMutableList())
    }
}