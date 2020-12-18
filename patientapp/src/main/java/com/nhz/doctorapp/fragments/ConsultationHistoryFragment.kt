package com.nhz.doctorapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nhz.doctorapp.R
import com.nhz.doctorapp.adapters.ConsultationHistoryAdapter
import com.nhz.doctorapp.fragments.dialogs.ConsultationPrescriptionDialogFragment
import com.nhz.doctorapp.fragments.dialogs.MedicalHistoryDialogFragment
import com.nhz.doctorapp.mvp.presenters.ConsultationHistoryPresenter
import com.nhz.doctorapp.mvp.presenters.impls.ConsultationHistoryPresenterImpl
import com.nhz.doctorapp.mvp.views.ConsultationHistoryView
import com.nhz.shared.data.vos.ConsultationsVO

class ConsultationHistoryFragment : Fragment(),ConsultationHistoryView {

    private lateinit var rViewConsultationList : RecyclerView
    private lateinit var btnConsultationBack : ImageView
    private lateinit var mLinearLayoutManager: LinearLayoutManager
    private lateinit var mAdapter : ConsultationHistoryAdapter
    private lateinit var mPresenter : ConsultationHistoryPresenter

    private lateinit var prescriptionFragment : ConsultationPrescriptionDialogFragment
    private lateinit var medicalHistoryFragment : MedicalHistoryDialogFragment

    companion object{
        fun newFragment() : ConsultationHistoryFragment{
            return ConsultationHistoryFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_consultation_history_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rViewConsultationList = view.findViewById(R.id.rViewConsultationList)
        btnConsultationBack = view.findViewById(R.id.ivConsultationHistoryBack)
        setUpPresenter()
        setUpRecyclerView()

        activity?.let {
            if (isAdded){
                mPresenter.onUiReady(it)
            }
        }
    }

    override fun showConsultationHistoryData(data: List<ConsultationsVO>) {
        mAdapter.addNewData(data.toMutableList())
    }

    override fun showPrescriptionDialogFragment(consultationId: String) {
        fragmentManager?.let {
            prescriptionFragment = ConsultationPrescriptionDialogFragment.newInstance(consultationId)
            prescriptionFragment.show(it,ConsultationPrescriptionDialogFragment.TAG_CONSULTATION_PRESCRIPTION)
        }
    }

    override fun showMedicalHistoryDialogFragment(consultationId: String,patientName : String,patientBd : String) {
        fragmentManager?.let {
            medicalHistoryFragment = MedicalHistoryDialogFragment.newInstance(consultationId,patientName,patientBd)
            medicalHistoryFragment.show(it,MedicalHistoryDialogFragment.TAG_MEDICAL_HISTORY)
        }
    }

    private fun setUpPresenter(){
        mPresenter = ViewModelProviders.of(this).get(ConsultationHistoryPresenterImpl::class.java)
        mPresenter.initPresenter(this)
    }

    private fun setUpRecyclerView(){
        activity?.let {
            mLinearLayoutManager = LinearLayoutManager(it,LinearLayoutManager.VERTICAL,false)
            mAdapter = ConsultationHistoryAdapter(mPresenter)

            rViewConsultationList.layoutManager = mLinearLayoutManager
            rViewConsultationList.adapter = mAdapter
        }
    }

}