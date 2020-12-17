package com.nhz.doctorapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nhz.doctorapp.R
import com.nhz.doctorapp.adapters.ConsultationHistoryAdapter
import com.nhz.doctorapp.adapters.ConsultationRequestAdapter
import com.nhz.doctorapp.mvp.presenters.impls.HomePresenterImpl
import com.nhz.doctorapp.mvp.presenters.interfaces.HomePresenter
import com.nhz.doctorapp.mvp.views.HomeView
import com.nhz.shared.data.vos.ConsultationRequestVO
import com.nhz.shared.data.vos.ConsultationsVO
import com.nhz.shared.data.vos.DoctorVO
import com.nhz.shared.data.vos.PatientVO

class HomeActivity : AppCompatActivity(),HomeView {

    private lateinit var rViewConsultationRequestList : RecyclerView
    private lateinit var rViewConsultationHistoryList : RecyclerView
    private lateinit var tvConsultationHistory : TextView
    private lateinit var tvDoctorName : TextView

    private lateinit var mPresenter : HomePresenter
    private lateinit var mConsultationRequestLayoutManager : LinearLayoutManager
    private lateinit var mConsultationHistoryLayoutManager : LinearLayoutManager
    private lateinit var mConsultationRequestAdapter : ConsultationRequestAdapter
    private lateinit var mConsultationHistoryAdapter : ConsultationHistoryAdapter

    companion object{
        fun newIntent(context : Context) : Intent{
            return Intent(context,HomeActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        rViewConsultationRequestList = findViewById(R.id.rViewConsultationRequestList)
        rViewConsultationHistoryList = findViewById(R.id.rViewConsultationHistoryList)
        tvConsultationHistory = findViewById(R.id.tvConsultationHistory)
        tvDoctorName = findViewById(R.id.tvDoctorName)

        setUpPresenter()
        setUpRecyclerView()
        mPresenter.onUiReady(this,this)

    }

    override fun showConsultationRequestData(data: List<ConsultationRequestVO>) {
        mConsultationRequestAdapter.addNewData(data.toMutableList())
    }

    override fun showConsultationHistoryData(data: List<ConsultationsVO>) {
        mConsultationHistoryAdapter.addNewData(data.toMutableList())
    }

    override fun showDoctorInfo(doctor: DoctorVO) {
        tvDoctorName.text = doctor.name
    }

    override fun navigateToPatientInfoActivity(patientVO: PatientVO,id : String) {
        startActivity(PatientInfoActivity.newIntent(
            patientVO.username,
            patientVO.userId,
            patientVO.date_of_birth,
            patientVO.image,
            id,
            this))
    }

    override fun showConsultationRequestList() {
        rViewConsultationRequestList.visibility = View.VISIBLE
    }

    override fun hideConsultationRequestList() {
        rViewConsultationRequestList.visibility = View.GONE
    }

    override fun showConsultationHistoryList() {
        tvConsultationHistory.visibility = View.VISIBLE
        rViewConsultationHistoryList.visibility = View.VISIBLE
    }

    override fun hideConsultationHistoryList() {
        tvConsultationHistory.visibility = View.GONE
        rViewConsultationHistoryList.visibility = View.GONE
    }

    private fun setUpPresenter(){
        mPresenter = ViewModelProviders.of(this).get(HomePresenterImpl::class.java)
        mPresenter.initPresenter(this)
    }

    private fun setUpRecyclerView(){
        mConsultationRequestLayoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        mConsultationHistoryLayoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)

        mConsultationRequestAdapter = ConsultationRequestAdapter(mPresenter)
        mConsultationHistoryAdapter = ConsultationHistoryAdapter()

        rViewConsultationRequestList.layoutManager = mConsultationRequestLayoutManager
        rViewConsultationRequestList.adapter = mConsultationRequestAdapter
        rViewConsultationHistoryList.layoutManager = mConsultationHistoryLayoutManager
        rViewConsultationHistoryList.adapter = mConsultationHistoryAdapter
    }
}