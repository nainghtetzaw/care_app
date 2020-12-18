package com.nhz.doctorapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProviders
import com.nhz.doctorapp.R
import com.nhz.doctorapp.mvp.presenters.impls.MedicalHistoryPresenterImpl
import com.nhz.doctorapp.mvp.presenters.interfaces.MedicalHistoryPresenter
import com.nhz.doctorapp.mvp.views.MedicalHistoryView
import com.nhz.shared.data.vos.MedicalHistoryVO

class MedicalHistoryDialogFragment : DialogFragment(),MedicalHistoryView {

    private var consultationId : String = ""
    private var patientName : String = ""
    private var patientBd : String = ""

    private lateinit var tvMedicalName : TextView
    private lateinit var tvMedicalBd : TextView
    private lateinit var tvMedicalDate : TextView
    private lateinit var tvInputMedicalHistory : TextView
    private lateinit var mPresenter : MedicalHistoryPresenter

    companion object{

        const val BUNDLE_CONSULTATION_ID = "BUNDLE_CONSULTATION_ID"
        const val BUNDLE_PATIENT_NAME = "BUNDLE_PATIENT_NAME"
        const val BUNDLE_PATIENT_BD = "BUNDLE_PATIENT_BD"
        const val TAG_MEDICAL = "TAG_MEDICAL"

        fun newInstance(consultationId : String,patientName : String,patientBd : String) : MedicalHistoryDialogFragment{
            return MedicalHistoryDialogFragment().apply {
                val bundle = Bundle()
                bundle.putString(BUNDLE_CONSULTATION_ID,consultationId)
                bundle.putString(BUNDLE_PATIENT_NAME,patientName)
                bundle.putString(BUNDLE_PATIENT_BD,patientBd)
                arguments = bundle
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            consultationId = it.getString(BUNDLE_CONSULTATION_ID,"")
            patientName = it.getString(BUNDLE_PATIENT_NAME,"")
            patientBd = it.getString(BUNDLE_PATIENT_BD,"")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_medical_history_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvMedicalName = view.findViewById(R.id.tvMedicalName)
        tvMedicalDate = view.findViewById(R.id.tvMedicalDate)
        tvInputMedicalHistory = view.findViewById(R.id.tvInputMedicalHistory)
        tvMedicalBd = view.findViewById(R.id.tvMedicalBd)
        setUpPresenter()
        mPresenter.onUiReady(consultationId)
    }

    override fun onResume() {
        super.onResume()
        val param = dialog?.window?.attributes?.apply {
            width = ViewGroup.LayoutParams.MATCH_PARENT
            height = ViewGroup.LayoutParams.WRAP_CONTENT
        }
        dialog?.window?.attributes = param
    }

    override fun showMedicalData(data: MedicalHistoryVO) {
        tvMedicalName.text = patientName
        tvMedicalBd.text = patientBd
        tvInputMedicalHistory.text = data.note
        tvMedicalDate.text = data.date_of_consultation
    }

    private fun setUpPresenter(){
        mPresenter = ViewModelProviders.of(this).get(MedicalHistoryPresenterImpl::class.java)
        mPresenter.initPresenter(this)
    }

}