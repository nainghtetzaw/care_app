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
import com.nhz.doctorapp.adapters.ChatGeneralInfoAdapter
import com.nhz.doctorapp.adapters.ChatSpecialityInfoAdapter
import com.nhz.doctorapp.mvp.presenters.CaseSummaryPresenter
import com.nhz.doctorapp.mvp.presenters.impls.CaseSummaryPresenterImpl
import com.nhz.doctorapp.mvp.views.CaseSummaryView
import com.nhz.shared.data.vos.CaseSummaryVO

class CaseSummaryActivity : AppCompatActivity(),CaseSummaryView {

    private lateinit var rViewChatGeneralInfo : RecyclerView
    private lateinit var rViewChatSpecialityInfo : RecyclerView
    private lateinit var ivCaseSummaryBack : ImageView

    private lateinit var mGeneralAdapter : ChatGeneralInfoAdapter
    private lateinit var mSpecialityAdapter: ChatSpecialityInfoAdapter
    private lateinit var mGeneralLayoutManager: LinearLayoutManager
    private lateinit var mSpecialityLayoutManager: LinearLayoutManager
    private lateinit var mPresenter : CaseSummaryPresenter

    companion object{

        const val CONSULTATION_ID = "CONSULTATION_ID"

        fun newIntent(consultationId : String,context : Context) : Intent{
            return Intent(context,CaseSummaryActivity::class.java)
                .putExtra(CONSULTATION_ID,consultationId)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_case_summary)

        rViewChatGeneralInfo = findViewById(R.id.rViewChatGeneralInfo)
        rViewChatSpecialityInfo = findViewById(R.id.rViewChatSpecialityInfo)
        ivCaseSummaryBack = findViewById(R.id.ivCaseSummaryBack)
        setUpPresenter()
        setUpRecyclerView()

        val consultationId = intent.getStringExtra(CONSULTATION_ID)
        consultationId?.let { mPresenter.onUiReady(it) }
        ivCaseSummaryBack.setOnClickListener {
            finish()
        }

    }

    private fun setUpPresenter(){
        mPresenter = ViewModelProviders.of(this).get(CaseSummaryPresenterImpl::class.java)
        mPresenter.initPresenter(this)
    }

    private fun setUpRecyclerView(){
        mGeneralAdapter = ChatGeneralInfoAdapter()
        mSpecialityAdapter = ChatSpecialityInfoAdapter()
        mGeneralLayoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        mSpecialityLayoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)

        rViewChatGeneralInfo.adapter = mGeneralAdapter
        rViewChatGeneralInfo.layoutManager = mGeneralLayoutManager
        rViewChatSpecialityInfo.adapter = mSpecialityAdapter
        rViewChatSpecialityInfo.layoutManager = mSpecialityLayoutManager
    }

    override fun showGeneralInfo(data: List<CaseSummaryVO>) {
        mGeneralAdapter.addNewData(data.toMutableList())
    }

    override fun showSpecialityInfo(data: List<CaseSummaryVO>) {
        mSpecialityAdapter.addNewData(data.toMutableList())
    }
}