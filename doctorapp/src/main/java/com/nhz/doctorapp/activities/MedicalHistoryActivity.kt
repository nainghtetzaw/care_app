package com.nhz.doctorapp.activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProviders
import com.nhz.doctorapp.R
import com.nhz.doctorapp.mvp.presenters.impls.MedicalHistoryPresenterImpl
import com.nhz.doctorapp.mvp.presenters.interfaces.MedicalHistoryPresenter
import com.nhz.doctorapp.mvp.views.MedicalHistoryView
import com.nhz.shared.data.vos.MedicalHistoryVO
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

class MedicalHistoryActivity : AppCompatActivity(),MedicalHistoryView {

    private lateinit var ivMedicalHistoryBack : ImageView
    private lateinit var tvMedicalName : TextView
    private lateinit var tvMedicalBd : TextView
    private lateinit var tvMedicalDate : TextView
    private lateinit var etInputMedicalHistory : EditText
    private lateinit var btnSaveMedicalHistory : Button

    private lateinit var mPresenter : MedicalHistoryPresenter

    companion object{

        const val CONSULTATION_ID = "CONSULTATION_ID"
        const val PATIENT_NAME = "PATIENT_NAME"
        const val PATIENT_BD = "PATIENT_BD"

        fun newIntent(consultationId : String,patientName : String,patientBd : String,context: Context) : Intent {
            return Intent(context,MedicalHistoryActivity::class.java)
                    .putExtra(CONSULTATION_ID,consultationId)
                    .putExtra(PATIENT_NAME,patientName)
                    .putExtra(PATIENT_BD,patientBd)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medical_history)

        ivMedicalHistoryBack = findViewById(R.id.ivMedicalHistoryBack)
        tvMedicalName = findViewById(R.id.tvMedicalName)
        tvMedicalBd = findViewById(R.id.tvMedicalBd)
        tvMedicalDate = findViewById(R.id.tvMedicalDate)
        etInputMedicalHistory = findViewById(R.id.etInputMedicalHistory)
        btnSaveMedicalHistory = findViewById(R.id.btnSaveMedicalHistory )

        val consultationId = intent.getStringExtra(CONSULTATION_ID)
        val patientName = intent.getStringExtra(PATIENT_NAME)
        val patientBd = intent.getStringExtra(PATIENT_BD)
        setUpPresenter()
        setPatientInfo(patientName!!,patientBd!!)

        btnSaveMedicalHistory.setOnClickListener {
            mPresenter.addMedicalHistory(consultationId!!,getCurrentDate(),etInputMedicalHistory.text.toString())
            finish()
        }
        ivMedicalHistoryBack.setOnClickListener {
            finish()
        }

    }

    private fun setUpPresenter(){
        mPresenter = ViewModelProviders.of(this).get(MedicalHistoryPresenterImpl::class.java)
        mPresenter.initPresenter(this)
    }

    private fun setPatientInfo(name : String,bd : String){
        tvMedicalName.text = name
        tvMedicalBd.text = bd
        tvMedicalDate.text = getCurrentDate()
    }

    @SuppressLint("SimpleDateFormat")
    private fun getCurrentDate() : String{
        val date = Date()
        val formatter = SimpleDateFormat("dd MMM yyyy EEE")
        return formatter.format(date)
    }

    override fun showMedicalData(data: MedicalHistoryVO) {

    }
}