package com.nhz.doctorapp.fragments.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProviders
import com.nhz.doctorapp.R
import com.nhz.doctorapp.mvp.presenters.MedicalHistoryPresenter
import com.nhz.doctorapp.mvp.presenters.impls.MedicalHistoryPresenterImpl
import com.nhz.doctorapp.mvp.views.MedicalHistoryView
import com.nhz.shared.data.vos.MedicalHistoryVO

class MedicalHistoryDialogFragment : DialogFragment(),MedicalHistoryView {

    private var mConsultationId : String = ""
    private var mPatientName : String = ""
    private var mPatientBd : String = ""

    private lateinit var tvInputMedicalHistory : TextView
    private lateinit var tvMedicalName : TextView
    private lateinit var tvMedicalBd : TextView
    private lateinit var tvMedicalDate : TextView

    private lateinit var mPresenter : MedicalHistoryPresenter

    companion object{

        const val BUNDLE_CONSULTATION_ID = "BUNDLE_CONSULTATION_ID"
        const val BUNDLE_PATIENT_NAME = "BUNDLE_PATIENT_NAME"
        const val BUNDLE_PATIENT_ID = "BUNDLE_PATIENT_ID"
        const val TAG_MEDICAL_HISTORY = "TAG_MEDICAL_HISTORY"

        fun newInstance(consultationId : String,patientName : String,patientBd : String) : MedicalHistoryDialogFragment{
            return MedicalHistoryDialogFragment().apply {
                val bundle = Bundle()
                bundle.putString(BUNDLE_CONSULTATION_ID,consultationId)
                bundle.putString(BUNDLE_PATIENT_NAME,patientName)
                bundle.putString(BUNDLE_PATIENT_ID,patientBd)
                arguments = bundle
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_medical_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvMedicalName = view.findViewById(R.id.tvMedicalName)
        tvMedicalDate = view.findViewById(R.id.tvMedicalDate)
        tvMedicalBd = view.findViewById(R.id.tvMedicalBd)
        tvInputMedicalHistory = view.findViewById(R.id.tvInputMedicalHistory)
        setUpPresenter()
        mPresenter.onUiReady(mPatientName,mPatientBd,mConsultationId)
    }

    override fun onResume() {
        super.onResume()
        val param = dialog?.window?.attributes?.apply {
            width = ViewGroup.LayoutParams.MATCH_PARENT
            height = ViewGroup.LayoutParams.WRAP_CONTENT
        }
        dialog?.window?.attributes = param
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mConsultationId = it.getString(BUNDLE_CONSULTATION_ID,"")
            mPatientName = it.getString(BUNDLE_PATIENT_NAME,"")
            mPatientBd = it.getString(BUNDLE_PATIENT_ID,"")
        }
    }

    override fun showMedicalHistoryData(
        name: String,
        bd: String,
        medicalHistory: MedicalHistoryVO
    ) {
        tvMedicalName.text = name
        tvMedicalBd.text = bd
        tvMedicalDate.text = medicalHistory.date_of_consultation
        tvInputMedicalHistory.text = medicalHistory.note
    }

    private fun setUpPresenter(){
        mPresenter = ViewModelProviders.of(this).get(MedicalHistoryPresenterImpl::class.java)
        mPresenter.initPresenter(this)
    }
}