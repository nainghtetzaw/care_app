package com.nhz.doctorapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.nhz.doctorapp.activities.ChatActivity
import com.nhz.doctorapp.R
import com.nhz.doctorapp.adapters.RecentDoctorAdapter
import com.nhz.doctorapp.adapters.SpecialitiesAdapter
import com.nhz.doctorapp.fragments.dialogs.ConfirmConsultationDialogFragment
import com.nhz.doctorapp.mvp.presenters.HomePresenter
import com.nhz.doctorapp.mvp.presenters.impls.HomePresenterImpl
import com.nhz.doctorapp.mvp.views.HomeView
import com.nhz.shared.data.vos.DoctorVO
import com.nhz.shared.data.vos.SpecialitiesVO

class HomeFragment : Fragment(),HomeView{

    private lateinit var mLinearLayoutManager: LinearLayoutManager
    private lateinit var mGridLayoutManager : GridLayoutManager
    private lateinit var mRecentDoctorAdapter : RecentDoctorAdapter
    private lateinit var mSpecialitiesAdapter : SpecialitiesAdapter
    private lateinit var mPresenter : HomePresenter

    private lateinit var rViewRecentDoctor : RecyclerView
    private lateinit var rViewSpeciality : RecyclerView
    private lateinit var tvRecentDoctorTitle : TextView
    private lateinit var tvAcceptedDoctor : TextView
    private lateinit var ivDoctorProfile : ImageView
    private lateinit var tvDoctorName : TextView
    private lateinit var tvDoctorSpeciality : TextView
    private lateinit var tvStartConsultation : TextView
    private lateinit var acceptedRequestViewPod : View
    private lateinit var mConfirmConsultationDialogFragment : ConfirmConsultationDialogFragment

    private var consultationId : String? = ""

    companion object{
        const val BUNDLE_CONSULTATION_ID = "BUNDLE_CONSULTATION_ID"

        fun newInstance(consultationId : String?) : HomeFragment{
            val bundle = Bundle()
            bundle.putString(BUNDLE_CONSULTATION_ID,consultationId)
            return HomeFragment().apply {
                arguments = bundle
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpPresenter()
        setUpRecyclerViews()

        tvRecentDoctorTitle = view.findViewById(R.id.tvRecentDoctorTitle)
        tvRecentDoctorTitle.visibility = View.GONE

        activity?.let { context ->

            tvStartConsultation = context.findViewById(R.id.tvStartConsultation)

            mPresenter.onUiReady(context,this)

            mPresenter.getAcceptedConsultation(false)

            tvStartConsultation.setOnClickListener {
                startActivity(ChatActivity.newIntent(context))
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        consultationId = arguments?.getString(BUNDLE_CONSULTATION_ID)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun showConfirmDialog(specialitId : Int, specialityType : String,oldOrNew : Boolean) {
        fragmentManager?.let {
            mConfirmConsultationDialogFragment = ConfirmConsultationDialogFragment.newFragment(specialityType,specialitId,oldOrNew)
            mConfirmConsultationDialogFragment.show(it,ConfirmConsultationDialogFragment.TAB_CONFIRM_CONSULTATION)
        }
    }

    override fun showSpecialitiesData(data: List<SpecialitiesVO>) {
        mSpecialitiesAdapter.addNewData(data.toMutableList())
    }

    override fun showRecentDoctorData(data: List<DoctorVO>) {
        mRecentDoctorAdapter.addNewData(data.toMutableList())
    }

    override fun showConsultationAcceptedDoctor(doctor: DoctorVO) {
        activity?.let {
            tvDoctorName = it.findViewById(R.id.tvDoctorName)
            ivDoctorProfile = it.findViewById(R.id.ivDoctorProfile)
            tvAcceptedDoctor = it.findViewById(R.id.tvAcceptedDoctor)
            tvDoctorSpeciality = it.findViewById(R.id.tvDoctorSpeciality)

            if (doctor.profileImage != ""){
                Glide.with(this)
                    .load(doctor.profileImage)
                    .into(ivDoctorProfile)
            }
            tvDoctorName.text = doctor.name
            tvDoctorSpeciality.text = doctor.specialityType
            tvAcceptedDoctor.text = "ဆေးနွေးမှုပြုလုပ်ရန် ${doctor.name} မှလက်ခံထားပါသည်။"
        }
    }

    override fun networkError(error: String) {
        activity?.let {
            Snackbar.make(CoordinatorLayout(it),error,Snackbar.LENGTH_SHORT).show()
        }
    }

    override fun hideAcceptedRequestViewPod() {
        activity?.let {
            acceptedRequestViewPod = it.findViewById(R.id.acceptedRequestDoctorViewPod)
            acceptedRequestViewPod.visibility = View.GONE
        }
    }

    override fun hideRecentDoctorList() {
        activity?.let {
            tvRecentDoctorTitle = it.findViewById(R.id.tvRecentDoctorTitle)
            tvRecentDoctorTitle.visibility = View.GONE
            rViewRecentDoctor.visibility = View.GONE
        }
    }

    override fun showAcceptedRequestViewPod() {
        activity?.let {
            acceptedRequestViewPod = it.findViewById(R.id.acceptedRequestDoctorViewPod)
            acceptedRequestViewPod.visibility = View.VISIBLE
        }
    }

    override fun showRecentDoctorList() {
        activity?.let {

            tvRecentDoctorTitle.visibility = View.VISIBLE
            rViewRecentDoctor.visibility = View.VISIBLE
        }
    }

    private fun setUpPresenter(){
        mPresenter = ViewModelProviders.of(this).get(HomePresenterImpl::class.java)
        mPresenter.initPresenter(this)
    }

    private fun setUpRecyclerViews(){
        activity?.let {
            rViewRecentDoctor =  it.findViewById(R.id.rviewRecentDoctor)
            rViewSpeciality = it.findViewById(R.id.rviewSpecialities)

            mGridLayoutManager = GridLayoutManager(it,2)
            mLinearLayoutManager = LinearLayoutManager(it,LinearLayoutManager.HORIZONTAL,false)

            mRecentDoctorAdapter = RecentDoctorAdapter()
            mSpecialitiesAdapter = SpecialitiesAdapter(mPresenter)

            rViewRecentDoctor.layoutManager = mLinearLayoutManager
            rViewRecentDoctor.adapter = mRecentDoctorAdapter

            rViewSpeciality.layoutManager = mGridLayoutManager
            rViewSpeciality.adapter = mSpecialitiesAdapter
        }
    }
}