package com.nhz.doctorapp.mvp.presenters.impls

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.nhz.doctorapp.mvp.presenters.HomePresenter
import com.nhz.doctorapp.mvp.views.HomeView
import com.nhz.shared.data.vos.CaseSummaryVO
import com.nhz.shared.data.vos.SpecialitiesVO
import com.nhz.shared.mvp.presenters.AbstractBasePresenter

class  HomePresenterImpl : HomePresenter,AbstractBasePresenter<HomeView>() {

    override fun onUiReady(context: Context, lifecycleOwner: LifecycleOwner) {
        mView?.hideAcceptedRequestViewPod()
        getSpecialitiesData(lifecycleOwner)
        getRecentDoctors(lifecycleOwner)
    }

    override fun getAcceptedConsultation(finished: Boolean) {
        getConsultation(finished)
    }

    override fun onTapSpeciality(spciality: SpecialitiesVO,oldOrNew : Boolean) {
        mView?.showConfirmDialog(spciality.id,spciality.name,oldOrNew)
    }

    private fun getSpecialitiesData(lifecycleOwner: LifecycleOwner){
        mModel.getSpecialitiesListFromDatabase().observe(lifecycleOwner, Observer {
            mView?.showSpecialitiesData(it)
        })
    }

    private fun getRecentDoctors(lifecycleOwner: LifecycleOwner){
        //userId
        mModel.getRecentDoctorsAndSaveToDatabase("72JXNg3bVUZ0FRyanMNiNm2WLPn1",{
            if (it.count() != 0){
                mView?.showRecentDoctorList()
                mView?.showRecentDoctorData(it)
            }else {
                mView?.hideRecentDoctorList()
            }

        },{
            mView?.hideRecentDoctorList()
            mView?.networkError(it)
        })
    }

    private fun getConsultation(finished : Boolean){
        mModel.getUnfinishedConsultationFromNetwork("72JXNg3bVUZ0FRyanMNiNm2WLPn1",finished,{
            val accepted = it.filter { consultation -> consultation.accept && !consultation.finished}
            if (accepted.count() != 0){
                accepted.forEach { consultationsVO ->
                    mView?.showAcceptedRequestViewPod()
                    consultationsVO.doctor_info?.let { doctor ->
                        mView?.showConsultationAcceptedDoctor(doctor)
                    }
                }
            }else{
                mView?.hideAcceptedRequestViewPod()
            }
        },{
            mView?.hideAcceptedRequestViewPod()
        })
    }

}