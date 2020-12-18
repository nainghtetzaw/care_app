package com.nhz.doctorapp.mvp.presenters.impls

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.nhz.doctorapp.mvp.presenters.ConfirmCaseSummaryPresenter
import com.nhz.doctorapp.mvp.views.ConfirmCaseSummaryView
import com.nhz.shared.data.vos.CaseSummaryVO
import com.nhz.shared.data.vos.ConsultationsVO
import com.nhz.shared.data.vos.DoctorVO
import com.nhz.shared.mvp.presenters.AbstractBasePresenter

class ConfirmCaseSummaryPresenterImpl : AbstractBasePresenter<ConfirmCaseSummaryView>(),ConfirmCaseSummaryPresenter{

    private lateinit var mCaseSummary : List<CaseSummaryVO>

    override fun onUiReady(id : String,context: Context, lifecycleOwner: LifecycleOwner) {
        getPatientGeneralQuestionsAndAnswers(lifecycleOwner)
        getSpecialityQuestionsAndAnswers(mAuthModel.getUserToken(),lifecycleOwner)
    }

    override fun navigateToMainActivity(id: String,lifecycleOwner: LifecycleOwner) {
        mModel.getPatientInfoFromDatabase().observe(lifecycleOwner, Observer {
            mModel.addConsultation(ConsultationsVO(id,"",mAuthModel.getUserToken(),DoctorVO(),it,false))
        })
        mCaseSummary.forEach {
            mModel.addConsultationCaseSummary(id,it)
        }
        mView?.onStartRequest(id)
    }

    private fun getPatientGeneralQuestionsAndAnswers(lifecycleOwner: LifecycleOwner){
        mModel.getPatientGeneralAnswersAndSaveToDatabase(mAuthModel.getUserToken(),{
            mView?.showGeneralQuestionsAndAnswers(it)
        },{})
    }

    private fun getSpecialityQuestionsAndAnswers(id : String,lifecycleOwner: LifecycleOwner){
        mModel.getRequestedPatientCaseSummaryAndSaveToDatabase(id,{
            mCaseSummary = it
            mView?.showSpecialityQuestionsAndAnswers(it)
        },{})
    }
}