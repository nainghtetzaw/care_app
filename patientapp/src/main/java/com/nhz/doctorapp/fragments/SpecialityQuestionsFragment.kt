package com.nhz.doctorapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nhz.doctorapp.R
import com.nhz.doctorapp.adapters.SpecialityQuestionsAdapter
import com.nhz.doctorapp.fragments.dialogs.ConfirmCaseSummaryDialogFragment
import com.nhz.doctorapp.mvp.presenters.SpecialityQuestionsPresenter
import com.nhz.doctorapp.mvp.presenters.impls.SpecialityQuestionsPresenterImpl
import com.nhz.doctorapp.mvp.views.SpecialityQuestionView
import com.nhz.shared.data.vos.SpecialityQuestionsVO


class SpecialityQuestionsFragment : Fragment(),SpecialityQuestionView{

    private lateinit var mPresenter : SpecialityQuestionsPresenter
    private lateinit var mLayoutManager: LinearLayoutManager
    private lateinit var mAdapter : SpecialityQuestionsAdapter
    private lateinit var rViewSpecialityQuestion : RecyclerView
    private lateinit var btnStartConsultation : Button

    private lateinit var mConfirmCaseSummaryDialogFragment : ConfirmCaseSummaryDialogFragment
    private var specialityId : Int = 0
    private var consultationId : String = ""
    private var doctorId : String = ""

    companion object{

        const val SPECIALITY_ID = "SPECIALITY_ID"
        const val CONSULTATION_ID = "CONSULTATION_ID"
        const val DOCTOR_ID = "DOCTOR_ID"

        fun newInstance(specialityid : Int,consultationId : String,doctorId : String) : SpecialityQuestionsFragment{
            val bundle = Bundle()
            bundle.putInt(SPECIALITY_ID,specialityid)
            bundle.putString(CONSULTATION_ID,consultationId)
            bundle.putString(DOCTOR_ID,doctorId)
            return SpecialityQuestionsFragment().apply {
                arguments = bundle
            }
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_speciality_questions, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpPresenter()
        setUpRecyclerView()

        btnStartConsultation = view.findViewById(R.id.btnStartConsultation)

        mPresenter.onUiReady(specialityId,consultationId,view.context,this)
        btnStartConsultation.setOnClickListener {
            mPresenter.createConsultationRequest(specialityId,consultationId,doctorId,this)
            mPresenter.showConfirmDialogAndSendAnswersToNetwork(mAdapter.getCaseSummary())
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            specialityId = it.getInt(SPECIALITY_ID,0)
            consultationId = it.getString(CONSULTATION_ID,"")
            doctorId = it.getString(DOCTOR_ID,"")
        }
    }

    override fun showSpecialityQuestions(data: List<SpecialityQuestionsVO>) {
        mAdapter.addNewData(data.toMutableList())
    }

    override fun onStartConsultation(id: String) {
        fragmentManager?.let {
            mConfirmCaseSummaryDialogFragment = ConfirmCaseSummaryDialogFragment.newFragment(id)
            mConfirmCaseSummaryDialogFragment.show(it,ConfirmCaseSummaryDialogFragment.TAG_CONFIRM_CASE_SUMMARY)
        }
    }

    private fun setUpPresenter(){
        mPresenter = ViewModelProviders.of(this).get(SpecialityQuestionsPresenterImpl::class.java)
        mPresenter.initPresenter(this)
    }

    private fun setUpRecyclerView(){
        activity?.let {
            rViewSpecialityQuestion = it.findViewById(R.id.rViewSpecialityQuestions)
            mLayoutManager = LinearLayoutManager(it,LinearLayoutManager.VERTICAL,false)
            mAdapter = SpecialityQuestionsAdapter()

            rViewSpecialityQuestion.adapter = mAdapter
            rViewSpecialityQuestion.layoutManager = mLayoutManager
        }
    }

}