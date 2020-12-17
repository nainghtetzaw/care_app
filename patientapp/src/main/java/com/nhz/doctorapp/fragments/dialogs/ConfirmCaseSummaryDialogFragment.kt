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
import com.nhz.doctorapp.activities.MainActivity
import com.nhz.doctorapp.adapters.ConfirmGeneralQuestionsAndAnswersAdapter
import com.nhz.doctorapp.adapters.ConfirmSpecialityInfoAdapter
import com.nhz.doctorapp.mvp.presenters.ConfirmCaseSummaryPresenter
import com.nhz.doctorapp.mvp.presenters.impls.ConfirmCaseSummaryPresenterImpl
import com.nhz.doctorapp.mvp.views.ConfirmCaseSummaryView
import com.nhz.shared.data.vos.CaseSummaryVO

class ConfirmCaseSummaryDialogFragment : DialogFragment(),ConfirmCaseSummaryView {

    private lateinit var rViewConfirmGeneralInfo : RecyclerView
    private lateinit var rViewConfirmSpecialityInfo : RecyclerView
    private lateinit var btnConfirmStartConsultation : Button

    private lateinit var mPresenter : ConfirmCaseSummaryPresenter
    private lateinit var mGeneralLayoutManager : LinearLayoutManager
    private lateinit var mSpecialityLayoutManager : LinearLayoutManager
    private lateinit var mGeneralAdapter : ConfirmGeneralQuestionsAndAnswersAdapter
    private lateinit var mSpecialityAdapter : ConfirmSpecialityInfoAdapter

    private var consultationId : String = ""

    companion object{

        const val TAG_CONFIRM_CASE_SUMMARY = "TAG_CONFIRM_CASE_SUMMARY"
        const val BUNDLE_CONSULTATION_ID = "BUNDLE_CONSULTATION_ID"

        fun newFragment(consultationId : String) : ConfirmCaseSummaryDialogFragment{
            val bundle = Bundle()
            bundle.putString(BUNDLE_CONSULTATION_ID,consultationId)
            return ConfirmCaseSummaryDialogFragment().apply {
                arguments = bundle
            }
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_confirm_case_summary_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpPresenter()

        rViewConfirmGeneralInfo = view.findViewById(R.id.rViewConfirmGeneralInfo)
        rViewConfirmSpecialityInfo = view.findViewById(R.id.rViewConfirmSpecialityInfo)
        btnConfirmStartConsultation = view.findViewById(R.id.btnConfirmStartConsultation)

        setUpRecyclerView()

        activity?.let { context ->
            mPresenter.onUiReady(consultationId,context,this)

            btnConfirmStartConsultation.setOnClickListener { view ->
                mPresenter.navigateToMainActivity(consultationId,this)
                dismiss()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            consultationId = it.getString(BUNDLE_CONSULTATION_ID,"")
        }
    }

    override fun showGeneralQuestionsAndAnswers(data: List<CaseSummaryVO>) {
        mGeneralAdapter.addNewData(data.toMutableList())
    }

    override fun showSpecialityQuestionsAndAnswers(data: List<CaseSummaryVO>) {
        mSpecialityAdapter.addNewData(data.toMutableList())
    }

    override fun onStartRequest(id: String) {
        activity?.let {
            startActivity(MainActivity.newIntent(it))
        }
    }

    private fun setUpPresenter(){
        mPresenter = ViewModelProviders.of(this).get(ConfirmCaseSummaryPresenterImpl::class.java)
        mPresenter.initPresenter(this)
    }

    private fun setUpRecyclerView(){
        activity?.let {
            mGeneralLayoutManager = LinearLayoutManager(it,LinearLayoutManager.VERTICAL,false)
            mSpecialityLayoutManager = LinearLayoutManager(it,LinearLayoutManager.VERTICAL,false)
            mGeneralAdapter = ConfirmGeneralQuestionsAndAnswersAdapter()
            mSpecialityAdapter = ConfirmSpecialityInfoAdapter()

            rViewConfirmGeneralInfo.layoutManager = mGeneralLayoutManager
            rViewConfirmGeneralInfo.adapter = mGeneralAdapter
            rViewConfirmSpecialityInfo.layoutManager = mSpecialityLayoutManager
            rViewConfirmSpecialityInfo.adapter = mSpecialityAdapter
        }
    }

}