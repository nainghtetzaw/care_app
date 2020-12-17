package com.nhz.doctorapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nhz.doctorapp.R
import com.nhz.doctorapp.adapters.QuestionsAdapter
import com.nhz.doctorapp.mvp.presenters.impls.SpecialityQuestionsPresenterImpl
import com.nhz.doctorapp.mvp.presenters.interfaces.SpecialityQuestionsPresenter
import com.nhz.doctorapp.mvp.views.SpecialityQuestionsView
import com.nhz.shared.data.vos.SpecialityQuestionsVO

class SpecialityQuestionsActivity : AppCompatActivity(),SpecialityQuestionsView {

    private lateinit var ivSpecialityQuestionsBack : ImageView
    private lateinit var rViewSpecialityQuestions : RecyclerView

    private lateinit var mAdapter : QuestionsAdapter
    private lateinit var mLayoutManager: LinearLayoutManager
    private lateinit var mPresenter : SpecialityQuestionsPresenter

    companion object{

        const val SPECIALITY_ID = "SPECIALITY_ID"
        const val CONSULTATION_ID = "CONSULTATION_ID"

        fun newIntent(specialityId : Int,consultationId : String,context: Context) : Intent{
            return Intent(context,SpecialityQuestionsActivity::class.java)
                    .putExtra(SPECIALITY_ID,specialityId)
                    .putExtra(CONSULTATION_ID,consultationId)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_speciality_questions_activiy)

        ivSpecialityQuestionsBack = findViewById(R.id.ivSpecialityQuestionsBack)
        rViewSpecialityQuestions = findViewById(R.id.rViewSpecialityQuestions)
        setUpPresenter()
        setUpRecyclerView()

        val specialityId = intent.getIntExtra(SPECIALITY_ID,0)
        val consultationId = intent.getStringExtra(CONSULTATION_ID)

        mPresenter.onUiReady(specialityId,consultationId!!)
        ivSpecialityQuestionsBack.setOnClickListener { finish() }
    }

    override fun showQuestions(data: List<SpecialityQuestionsVO>) {
        mAdapter.addNewData(data.toMutableList())
    }

    private fun setUpPresenter(){
        mPresenter = ViewModelProviders.of(this).get(SpecialityQuestionsPresenterImpl::class.java)
        mPresenter.initPresenter(this)
    }

    private fun setUpRecyclerView(){
        mLayoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        mAdapter = QuestionsAdapter(mPresenter)

        rViewSpecialityQuestions.layoutManager = mLayoutManager
        rViewSpecialityQuestions.adapter = mAdapter
    }

}