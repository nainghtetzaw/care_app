package com.nhz.doctorapp.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nhz.doctorapp.R
import com.nhz.doctorapp.activities.CaseSummaryFormActivity
import com.nhz.doctorapp.adapters.GeneralQuestionsAdapter
import com.nhz.doctorapp.adapters.GeneralQuestionsAndAnswersAdapter
import com.nhz.doctorapp.mvp.presenters.GeneralQuestionsPresenter
import com.nhz.doctorapp.mvp.presenters.impls.GeneralQuestionsPresenterImpl
import com.nhz.doctorapp.mvp.views.GeneralQuestionsView
import com.nhz.shared.data.vos.CaseSummaryVO
import com.nhz.shared.data.vos.GeneralQuestionsVO


class GeneralQuestionsFragment : Fragment(),GeneralQuestionsView {

    private lateinit var cardQuestionsAndAnswers : CardView
    private lateinit var rViewQuestionAndAnswer : RecyclerView
    private lateinit var rViewQuestionsList: RecyclerView
    private lateinit var btnNext : Button

    private lateinit var mLayoutManager : LinearLayoutManager
    private lateinit var mLinearLayoutManager: LinearLayoutManager
    private lateinit var mQuestionsAndAnswersAdapter : GeneralQuestionsAndAnswersAdapter
    private lateinit var mQuestionsAdapter : GeneralQuestionsAdapter
    private lateinit var mPresenter : GeneralQuestionsPresenter
    private var specialityId : Int = 0
    private var oldOrNew : Boolean = false

    companion object{

        const val SPECIALITY_ID = "SPECIALITY_ID"
        const val BUNDLE_OLD_OR_NEW = "BUNDLE_OLD_OR_NEW"
        fun newInstance(specialityId : Int,oldOrNew : Boolean) : GeneralQuestionsFragment {
            val bundle = Bundle()
            bundle.putInt(SPECIALITY_ID,specialityId)
            bundle.putBoolean(BUNDLE_OLD_OR_NEW,oldOrNew)
            return GeneralQuestionsFragment().apply {
                arguments = bundle
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_general_questions, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpPresenter()
        activity?.let {

            cardQuestionsAndAnswers = it.findViewById(R.id.cardQuestionsAndAnswers)
            rViewQuestionAndAnswer = it.findViewById(R.id.rViewQuestionsAndAnswers)
            rViewQuestionsList = it.findViewById(R.id.rViewQuestionsList)
            btnNext = it.findViewById(R.id.btnNext)

            setUpRecyclerView(it)

            mPresenter.onUiReady(it,this)

            btnNext.setOnClickListener {view ->

                activity?.findViewById<View>(R.id.vCaseSpeciality)?.background = ContextCompat.getDrawable(it,R.color.blue)
                activity?.findViewById<ImageView>(R.id.ivCaseSpeciality)?.background = ContextCompat.getDrawable(it,R.color.blue)

                fragmentManager?.beginTransaction()?.replace(R.id.fragmentContainer,SpecialityQuestionsFragment.newInstance(specialityId,oldOrNew))
                        ?.commit()

                mPresenter.sendGeneralAnswers(mQuestionsAdapter.getGeneralData())
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            specialityId = it.getInt(SPECIALITY_ID,0)
            oldOrNew = it.getBoolean(BUNDLE_OLD_OR_NEW,false)
        }
    }

    private fun setUpPresenter(){
        mPresenter = ViewModelProviders.of(this).get(GeneralQuestionsPresenterImpl::class.java)
        mPresenter.initPresenter(this)
    }

    private fun setUpRecyclerView(context: Context){
        mLayoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        mLinearLayoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        mQuestionsAndAnswersAdapter = GeneralQuestionsAndAnswersAdapter()
        mQuestionsAdapter = GeneralQuestionsAdapter(mPresenter)

        rViewQuestionAndAnswer.layoutManager = mLayoutManager
        rViewQuestionAndAnswer.adapter = mQuestionsAndAnswersAdapter

        rViewQuestionsList.layoutManager = mLinearLayoutManager
        rViewQuestionsList.adapter = mQuestionsAdapter
    }

    override fun showOneTimeQuestionsAndAnswers(data: List<CaseSummaryVO>) {
        mQuestionsAndAnswersAdapter.addNewData(data.toMutableList())
    }

    override fun showAlwaysQuestions(data: List<GeneralQuestionsVO>) {
        mQuestionsAdapter.addNewData(data.toMutableList())
    }

    override fun hideQuestionsAndAnswersList() {
        cardQuestionsAndAnswers.visibility = View.GONE
    }

    override fun showQuestionsAndAnswersList() {
        cardQuestionsAndAnswers.visibility = View.VISIBLE
    }
}