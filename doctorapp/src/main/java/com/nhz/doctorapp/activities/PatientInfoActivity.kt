package com.nhz.doctorapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nhz.doctorapp.R
import com.nhz.doctorapp.adapters.PatientGeneralInfoAdapter
import com.nhz.doctorapp.adapters.PatientSpecialityInfoAdapter
import com.nhz.doctorapp.mvp.presenters.impls.PatientInfoPresenterImpl
import com.nhz.doctorapp.mvp.presenters.interfaces.PatientInfoPresenter
import com.nhz.doctorapp.mvp.views.PatientInfoView
import com.nhz.shared.data.vos.CaseSummaryVO

class PatientInfoActivity : AppCompatActivity(),PatientInfoView {

    private lateinit var rViewPatientGeneralInfo : RecyclerView
    private lateinit var rViewPatientSpecialityInfo : RecyclerView
    private lateinit var ivPatientInfoProfile : ImageView
    private lateinit var tvPatientInfoName : TextView
    private lateinit var tvPatientInfoBd : TextView

    private lateinit var mGeneralLayoutManager : LinearLayoutManager
    private lateinit var mSpecialityLayoutManager : LinearLayoutManager
    private lateinit var mGeneralAdapter : PatientGeneralInfoAdapter
    private lateinit var mSpecialityAdapter : PatientSpecialityInfoAdapter
    private lateinit var mPresenter : PatientInfoPresenter

    companion object{

        const val PATIENT_NAME = "PATIENT_NAME"
        const val PATIENT_ID = "PATIENT_ID"
        const val PATIENT_IMAGE = "PATIENT_IMAGE"
        const val PATIENT_BD = "PATIENT_BD"
        const val CONSULTATION_ID = "CONSULTATION_ID"

        fun newIntent(patientName : String,patientId : String,patientBd : String,patientImage : String,id: String,context: Context) : Intent{
            return  Intent(context,PatientInfoActivity::class.java)
                .putExtra(PATIENT_NAME,patientName)
                .putExtra(PATIENT_BD,patientBd)
                .putExtra(PATIENT_ID,patientId)
                .putExtra(PATIENT_IMAGE,patientImage)
                .putExtra(CONSULTATION_ID,id)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_info)

        rViewPatientGeneralInfo = findViewById(R.id.rViewPatientGeneralInfo)
        rViewPatientSpecialityInfo = findViewById(R.id.rViewSpecialityInfo)
        ivPatientInfoProfile = findViewById(R.id.ivPatientInfoProfile)
        tvPatientInfoName = findViewById(R.id.tvPatientInfoName)
        tvPatientInfoBd = findViewById(R.id.tvPatientInfoBd)

        setUpPresenter()
        setUpRecyclerView()

        val patientName = intent.getStringExtra(PATIENT_NAME)
        val patientId = intent.getStringExtra(PATIENT_ID)
        val patientBd = intent.getStringExtra(PATIENT_BD)
        val patientImage = intent.getStringExtra(PATIENT_IMAGE)
        val consultationId = intent.getStringExtra(CONSULTATION_ID)

        mPresenter.onUiReady(patientId!!,consultationId!!,this,this)
        setPatientInfo(patientName!!,patientBd!!,patientImage!!)

    }

    private fun setUpPresenter(){
        mPresenter = ViewModelProviders.of(this).get(PatientInfoPresenterImpl::class.java)
        mPresenter.initPresenter(this)
    }

    private fun setPatientInfo(name : String,bd : String,image : String){
        if (image != ""){
            Glide.with(this)
                .load(image)
                .into(ivPatientInfoProfile)
        }
        tvPatientInfoName.text = name
        tvPatientInfoBd.text = bd
    }

    private fun setUpRecyclerView(){
        mGeneralLayoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        mSpecialityLayoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        mGeneralAdapter = PatientGeneralInfoAdapter()
        mSpecialityAdapter = PatientSpecialityInfoAdapter()

        rViewPatientGeneralInfo.layoutManager = mGeneralLayoutManager
        rViewPatientSpecialityInfo.layoutManager = mSpecialityLayoutManager

        rViewPatientGeneralInfo.adapter = mGeneralAdapter
        rViewPatientSpecialityInfo.adapter = mSpecialityAdapter
    }

    override fun showGeneralAnswerData(data: List<CaseSummaryVO>) {
        mGeneralAdapter.addNewData(data.toMutableList())
    }

    override fun showSpecialityAnswerData(data: List<CaseSummaryVO>) {
        mGeneralAdapter.addNewData(data.toMutableList())
    }
}