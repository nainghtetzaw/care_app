package com.nhz.doctorapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nhz.doctorapp.R
import com.nhz.doctorapp.adapters.ConsultationHistoryAdapter
import com.nhz.doctorapp.adapters.ConsultationRequestAdapter
import com.nhz.doctorapp.fragments.CaseSummaryHistoryDialogFragment
import com.nhz.doctorapp.fragments.MedicalHistoryDialogFragment
import com.nhz.doctorapp.fragments.PrescriptionHistoryDialogFragment
import com.nhz.doctorapp.fragments.SetConsultationTimeFragmentDialog
import com.nhz.doctorapp.mvp.presenters.impls.HomePresenterImpl
import com.nhz.doctorapp.mvp.presenters.interfaces.HomePresenter
import com.nhz.doctorapp.mvp.views.HomeView
import com.nhz.shared.data.vos.ConsultationRequestVO
import com.nhz.shared.data.vos.ConsultationsVO
import com.nhz.shared.data.vos.DoctorVO
import com.nhz.shared.data.vos.PatientVO

class HomeActivity : AppCompatActivity(),HomeView {

    private var isRequestVisible : Boolean = false
    private var isHistoryVisible : Boolean = false

    private lateinit var rViewConsultationRequestList : RecyclerView
    private lateinit var rViewConsultationHistoryList : RecyclerView
    private lateinit var tvConsultationHistory : TextView
    private lateinit var tvDoctorName : TextView
    private lateinit var ivMainDoctorProfile : ImageView
    private lateinit var ivEmpty : ImageView

    private lateinit var mPresenter : HomePresenter
    private lateinit var mSelectTimeFragment : SetConsultationTimeFragmentDialog
    private lateinit var mConsultationRequestLayoutManager : LinearLayoutManager
    private lateinit var mConsultationHistoryLayoutManager : LinearLayoutManager
    private lateinit var mConsultationRequestAdapter : ConsultationRequestAdapter
    private lateinit var mConsultationHistoryAdapter : ConsultationHistoryAdapter
    private lateinit var mMedicalHistoryDialogFragment: MedicalHistoryDialogFragment
    private lateinit var mPrescriptionHistoryDialogFragment: PrescriptionHistoryDialogFragment
    private lateinit var mCaseSummaryHistoryDialogFragment: CaseSummaryHistoryDialogFragment

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
        ivMainDoctorProfile = findViewById(R.id.ivMainDoctorProfile)
        ivEmpty = findViewById(R.id.ivEmpty)

        tvConsultationHistory.visibility = View.GONE

        setUpPresenter()
        setUpRecyclerView()
        mPresenter.onUiReady(this,this)

        ivMainDoctorProfile.setOnClickListener {
            startActivity(ProfileActivity.newIntent(this))
        }
    }


    override fun showConsultationRequestData(data: List<ConsultationRequestVO>,doctorId : String) {
        mConsultationRequestAdapter.setDoctorId(doctorId)
        mConsultationRequestAdapter.addNewData(data.toMutableList())
    }

    override fun showConsultationHistoryData(data: List<ConsultationsVO>) {
        mConsultationHistoryAdapter.addNewData(data.toMutableList())
    }

    override fun showDoctorInfo(doctor: DoctorVO) {
        tvDoctorName.text = doctor.name
        if (doctor.profileImage != ""){
            Glide.with(this)
                .load(doctor.profileImage)
                .into(ivMainDoctorProfile)
        }
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

    override fun showMedicalHistoryDialogFragment(consultationId: String,patientName : String,patientBd : String) {
        mMedicalHistoryDialogFragment = MedicalHistoryDialogFragment.newInstance(consultationId,patientName,patientBd)
        mMedicalHistoryDialogFragment.show(supportFragmentManager,MedicalHistoryDialogFragment.TAG_MEDICAL)
    }

    override fun showCaseSummaryHistoryDialogFragment(consultationId: String,patientId : String) {
        mCaseSummaryHistoryDialogFragment = CaseSummaryHistoryDialogFragment.newInstance(consultationId,patientId)
        mCaseSummaryHistoryDialogFragment.show(supportFragmentManager,CaseSummaryHistoryDialogFragment.TAG_CASE_SUMMARY_HISTORY)
    }

    override fun showPrescriptionHistoryDialogFragment(consultationId: String) {
        mPrescriptionHistoryDialogFragment = PrescriptionHistoryDialogFragment.newInstance(consultationId)
        mPrescriptionHistoryDialogFragment.show(supportFragmentManager,PrescriptionHistoryDialogFragment.TAG_PRESCRIPTION_HISTORY)
    }

    override fun showSetConsultationTimeFragmentDialog() {
        mSelectTimeFragment = SetConsultationTimeFragmentDialog.newInstance()
        mSelectTimeFragment.show(supportFragmentManager,SetConsultationTimeFragmentDialog.TAG_SELECT_TIME)
    }

    override fun navigateToChatActivity(patientName: String, patientId: String, patientBd: String, patientImage: String, consultationId: String) {
        startActivity(ChatActivity.newIntent(patientName,patientId,patientBd,patientImage,consultationId,this))
    }

    override fun showConsultationRequestList() {
        rViewConsultationRequestList.visibility = View.VISIBLE
        isRequestVisible = true
        showOrHideEmptyView()
    }

    override fun hideConsultationRequestList() {
        rViewConsultationRequestList.visibility = View.GONE
        isRequestVisible = false
        showOrHideEmptyView()
    }

    override fun showConsultationHistoryList() {
        tvConsultationHistory.visibility = View.VISIBLE
        rViewConsultationHistoryList.visibility = View.VISIBLE
        isHistoryVisible = true
        showOrHideEmptyView()
    }

    override fun hideConsultationHistoryList() {
        tvConsultationHistory.visibility = View.GONE
        rViewConsultationHistoryList.visibility = View.GONE
        isHistoryVisible = false
        showOrHideEmptyView()
    }

    override fun showEmpty() {
        ivEmpty.visibility = View.VISIBLE
    }

    override fun hideEmpty() {
        ivEmpty.visibility = View.GONE
    }

    private fun setUpPresenter(){
        mPresenter = ViewModelProviders.of(this).get(HomePresenterImpl::class.java)
        mPresenter.initPresenter(this)
    }

    private fun showOrHideEmptyView(){
        if (!isHistoryVisible && !isRequestVisible){
            ivEmpty.visibility = View.VISIBLE
        }else {
            ivEmpty.visibility = View.GONE
        }
    }

    private fun setUpRecyclerView(){
        mConsultationRequestLayoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        mConsultationHistoryLayoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)

        mConsultationRequestAdapter = ConsultationRequestAdapter(mPresenter)
        mConsultationHistoryAdapter = ConsultationHistoryAdapter(mPresenter)

        rViewConsultationRequestList.layoutManager = mConsultationRequestLayoutManager
        rViewConsultationRequestList.adapter = mConsultationRequestAdapter
        rViewConsultationHistoryList.layoutManager = mConsultationHistoryLayoutManager
        rViewConsultationHistoryList.adapter = mConsultationHistoryAdapter
    }
}