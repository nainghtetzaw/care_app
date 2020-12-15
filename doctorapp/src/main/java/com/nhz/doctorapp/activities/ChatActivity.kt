package com.nhz.doctorapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nhz.doctorapp.R
import com.nhz.doctorapp.adapters.ChatGeneralInfoAdapter
import com.nhz.doctorapp.adapters.ChatSpecialityInfoAdapter
import com.nhz.doctorapp.adapters.MessageListAdapter
import com.nhz.doctorapp.mvp.presenters.impls.ChatPresenterImpl
import com.nhz.doctorapp.mvp.presenters.interfaces.ChatPresenter
import com.nhz.doctorapp.mvp.views.ChatView
import com.nhz.shared.data.vos.*
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class ChatActivity : AppCompatActivity(),ChatView {

    private lateinit var rViewChatSpecialityInfo : RecyclerView
    private lateinit var rViewChatGeneralInfo : RecyclerView
    private lateinit var rViewMessageList : RecyclerView
    private lateinit var tvChatPatientName : TextView
    private lateinit var ivChatPatientProfile : ImageView
    private lateinit var tvChatPatientInfoName : TextView
    private lateinit var tvChatPatientInfoBd : TextView
    private lateinit var ivSendMessage : ImageView
    private lateinit var etInputMessage : EditText

    private lateinit var mSpecialityAdapter : ChatSpecialityInfoAdapter
    private lateinit var mGeneralAdapter : ChatGeneralInfoAdapter
    private lateinit var mMessageAdapter : MessageListAdapter
    private lateinit var mSpecialityLayoutManager : LinearLayoutManager
    private lateinit var mGeneralLayoutManager : LinearLayoutManager
    private lateinit var mMedicineLayoutManager: LinearLayoutManager
    private lateinit var mMessageLayoutManager: LinearLayoutManager
    private lateinit var mPresenter : ChatPresenter

    companion object{

        const val PATIENT_NAME = "PATIENT_NAME"
        const val PATIENT_ID = "PATIENT_ID"
        const val PATIENT_BD = "PATIENT_BD"
        const val PATIENT_IMAGE = "PATIENT_IMAGE"
        const val CONSULTATION_ID = "CONSULTATION_ID"

        fun newIntent(patientName : String,patientId : String,patientBd : String,patientImage: String,consultationId : String,context: Context) : Intent {
            return Intent(context,ChatActivity::class.java)
                    .putExtra(PATIENT_NAME,patientName)
                    .putExtra(PATIENT_ID,patientId)
                    .putExtra(PATIENT_BD,patientBd)
                    .putExtra(PATIENT_IMAGE,patientImage)
                    .putExtra(CONSULTATION_ID,consultationId)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        rViewChatSpecialityInfo = findViewById(R.id.rViewChatSpecialityInfo)
        rViewChatGeneralInfo = findViewById(R.id.rViewChatGeneralInfo)
        rViewMessageList = findViewById(R.id.rViewMessageList)
        tvChatPatientName = findViewById(R.id.tvChatPatientName)
        tvChatPatientInfoName = findViewById(R.id.tvChatPatientInfoName)
        tvChatPatientInfoBd = findViewById(R.id.tvChatPatientInfoBd)
        ivChatPatientProfile = findViewById(R.id.ivChatPatientProfile)
        ivSendMessage = findViewById(R.id.ivSendMessage)
        etInputMessage = findViewById(R.id.etInputMessage)
        setUpPresenter()
        setUpRecyclerView()

        val patientName = intent.getStringExtra(PATIENT_NAME)
        val patientId = intent.getStringExtra(PATIENT_ID)
        val patientBd = intent.getStringExtra(PATIENT_BD)
        val patientImage = intent.getStringExtra(PATIENT_IMAGE)
        val consultationId = intent.getStringExtra(CONSULTATION_ID)
        mPresenter.onUiReady(patientId!!,consultationId!!,this,this)

        if (patientImage != null) {
            setPatientInfo(patientName!!,patientImage,patientBd!!)
        }

        ivSendMessage.setOnClickListener {
            if (etInputMessage.text.isNotBlank() || etInputMessage.text.isNotEmpty()){
                if (patientImage != null) {
                    mPresenter.sendMessage(consultationId,etInputMessage.text.toString(),getTimeStamp(),patientName!!,patientId,patientImage)
                }
                etInputMessage.text.clear()
            }
        }

    }

    override fun showSpecialityQuestionAndAnswerData(data: List<CaseSummaryVO>) {
        mSpecialityAdapter.addNewData(data.toMutableList())
    }

    override fun showGeneralQuestionAndAnswerData(data: List<CaseSummaryVO>) {
        mGeneralAdapter.addNewData(data.toMutableList())
    }

    override fun showMessageList(data: List<LiveChatVO>,doctorId : String) {
        mMessageAdapter.addDoctorId(doctorId)
        mMessageAdapter.addNewData(data.toMutableList())
    }

    private fun setUpPresenter(){
        mPresenter = ViewModelProviders.of(this).get(ChatPresenterImpl::class.java)
        mPresenter.initPresenter(this)
    }

    private fun getTimeStamp() : String {
        val date = LocalTime.now()
        val formatter = DateTimeFormatter.ofPattern("hh:mm a")
        return date.format(formatter)
    }

    private fun setPatientInfo(name : String,image : String,bd : String){
        if (image != ""){
            Glide.with(this)
                    .load(image)
                    .into(ivChatPatientProfile)
        }
        tvChatPatientName.text = name
        tvChatPatientInfoBd.text = bd
        tvChatPatientInfoName.text = name
    }

    private fun setUpRecyclerView(){

        mSpecialityLayoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        mGeneralLayoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        mMessageLayoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        mSpecialityAdapter = ChatSpecialityInfoAdapter()
        mGeneralAdapter = ChatGeneralInfoAdapter()
        mMessageAdapter = MessageListAdapter()

        rViewChatSpecialityInfo.layoutManager = mSpecialityLayoutManager
        rViewChatGeneralInfo.layoutManager = mGeneralLayoutManager
        rViewMessageList.layoutManager = mMessageLayoutManager

        rViewChatSpecialityInfo.adapter = mSpecialityAdapter
        rViewChatGeneralInfo.adapter = mGeneralAdapter
        rViewMessageList.adapter = mMessageAdapter

    }

}