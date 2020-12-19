package com.nhz.doctorapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nhz.doctorapp.R
import com.nhz.doctorapp.adapters.ChatPatientGeneralInfoAdapter
import com.nhz.doctorapp.adapters.ChatPatientSpecialityInfoAdapter
import com.nhz.doctorapp.adapters.MessageListAdapter
import com.nhz.doctorapp.mvp.presenters.ChatPresenter
import com.nhz.doctorapp.mvp.presenters.impls.ChatPresenterImpl
import com.nhz.doctorapp.mvp.views.ChatView
import com.nhz.shared.data.vos.CaseSummaryVO
import com.nhz.shared.data.vos.DoctorVO
import com.nhz.shared.data.vos.LiveChatVO
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class ChatActivity : AppCompatActivity(),ChatView {

    private lateinit var tvChatDoctorName : TextView
    private lateinit var ivChatDoctorProfile : ImageView
    private lateinit var tvChatPatientInfoName : TextView
    private lateinit var tvChatPatientInfoBd : TextView
    private lateinit var ivSendMessage : ImageView
    private lateinit var etInputText : EditText
    private lateinit var rViewGeneralInfo : RecyclerView
    private lateinit var rViewSpecialityInfo : RecyclerView
    private lateinit var rViewMessageList : RecyclerView
    private lateinit var tvChatWatchMore : TextView
    private lateinit var nestedScrollView: NestedScrollView
    private lateinit var ivChatBack : ImageView

    private lateinit var mGeneralLayoutManager : LinearLayoutManager
    private lateinit var mSpecialityLayoutManager : LinearLayoutManager
    private lateinit var mMessageLayoutManager: LinearLayoutManager
    private lateinit var mGeneralAdapter : ChatPatientGeneralInfoAdapter
    private lateinit var mSpecialityAdapter: ChatPatientSpecialityInfoAdapter
    private lateinit var mMessageAdapter : MessageListAdapter
    private lateinit var mPresenter : ChatPresenter

    private var doctorName : String? = ""
    private var doctorImage : String? = ""

    companion object{

        const val CONSULTATION_ID = "CONSULTATION_ID"
        const val DOCTOR_NAME = "DOCTOR_NAME"
        const val DOCTOR_IMAGE = "DOCTOR_IMAGE"

        fun newIntent(consultationId : String,doctorName : String,doctorImage : String,context: Context) : Intent{
            return Intent(context, ChatActivity::class.java)
                    .putExtra(CONSULTATION_ID,consultationId)
                    .putExtra(DOCTOR_NAME,doctorName)
                    .putExtra(DOCTOR_IMAGE,doctorImage)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        tvChatDoctorName = findViewById(R.id.tvChatDoctorName)
        ivChatDoctorProfile = findViewById(R.id.ivChatDoctorProfile)
        tvChatPatientInfoName = findViewById(R.id.tvChatPatientInfoName)
        tvChatPatientInfoBd = findViewById(R.id.tvChatPatientInfoBd)
        ivSendMessage = findViewById(R.id.ivSendMessage)
        etInputText = findViewById(R.id.etInputMessage)
        rViewMessageList = findViewById(R.id.rViewMessageList)
        rViewGeneralInfo = findViewById(R.id.rViewChatGeneralInfo)
        rViewSpecialityInfo = findViewById(R.id.rViewChatSpecialityInfo)
        tvChatWatchMore = findViewById(R.id.tvChatWatchMore)
        nestedScrollView = findViewById(R.id.nestedScrollView)
        ivChatBack = findViewById(R.id.ivChatBack)
        setUpPresenter()
        setUpRecyclerView()

        val consultationId = intent.getStringExtra(CONSULTATION_ID)
        doctorName = intent.getStringExtra(DOCTOR_NAME)
        doctorImage = intent.getStringExtra(DOCTOR_IMAGE)

        mPresenter.onUiReady(consultationId!!,this,this)

        nestedScrollView.post { Runnable { nestedScrollView.fullScroll(View.FOCUS_UP) } }

        ivSendMessage.setOnClickListener {
            if(etInputText.text.isNotBlank() || etInputText.text.isNotEmpty()){
                mPresenter.sendMessage(consultationId,etInputText.text.toString(),getTimeStamp())
                etInputText.text.clear()
            }
        }

        tvChatWatchMore.setOnClickListener {
            startActivity(CaseSummaryActivity.newIntent(consultationId,this))
        }

        ivChatBack.setOnClickListener { finish() }
    }

    override fun showSpecialityQuestionAndAnswerData(data: List<CaseSummaryVO>) {
        mSpecialityAdapter.addNewData(data.toMutableList())
    }

    override fun showGeneralQuestionAndAnswerData(data: List<CaseSummaryVO>) {
        mGeneralAdapter.addNewData(data.toMutableList())
    }

    override fun showMessageList(data: List<LiveChatVO>, patientId : String) {
        mMessageAdapter.addPatientId(patientId)
        mMessageAdapter.addNewData(data.toMutableList())
    }

    override fun setDoctorAndPatientInfo(patientName : String,patientBd : String){
        tvChatPatientInfoName.text = patientName
        tvChatPatientInfoBd.text = patientBd
        tvChatDoctorName.text = doctorName
        if (doctorImage != ""){
            Glide.with(this)
                    .load(doctorImage)
                    .into(ivChatDoctorProfile)
        }
    }

    override fun navigateToOrderMedicineActivity(consultationId: String) {
        startActivity(OrderMedicinesActivity.newIntent(consultationId,this))
    }

    private fun setUpPresenter(){
        mPresenter = ViewModelProviders.of(this).get(ChatPresenterImpl::class.java)
        mPresenter.initPresenter(this)
    }

    private fun getTimeStamp() : String{
        val time = LocalTime.now()
        val formatter = DateTimeFormatter.ofPattern("hh:mm a")
        return time.format(formatter)
    }

    private fun setUpRecyclerView(){
        mGeneralLayoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        mSpecialityLayoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        mMessageLayoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        mGeneralAdapter  = ChatPatientGeneralInfoAdapter()
        mSpecialityAdapter = ChatPatientSpecialityInfoAdapter()
        mMessageAdapter = MessageListAdapter(mPresenter)

        rViewGeneralInfo.layoutManager = mGeneralLayoutManager
        rViewSpecialityInfo.layoutManager = mSpecialityLayoutManager
        rViewMessageList.layoutManager = mMessageLayoutManager
        rViewGeneralInfo.adapter = mGeneralAdapter
        rViewSpecialityInfo.adapter = mSpecialityAdapter
        rViewMessageList.adapter = mMessageAdapter
    }
}