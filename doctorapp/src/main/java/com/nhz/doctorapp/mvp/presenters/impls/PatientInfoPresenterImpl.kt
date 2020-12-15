package com.nhz.doctorapp.mvp.presenters.impls

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.nhz.doctorapp.mvp.presenters.interfaces.PatientInfoPresenter
import com.nhz.doctorapp.mvp.views.PatientInfoView
import com.nhz.shared.mvp.presenters.AbstractBasePresenter

class PatientInfoPresenterImpl : AbstractBasePresenter<PatientInfoView>(),PatientInfoPresenter {
    override fun onUiReady(patientId: String,consultationId: String,context: Context, lifecycleOwner: LifecycleOwner) {
        getRequestedGeneralCaseSummary(patientId)
        getRequestedSpecialityCaseSummary(patientId)
    }

    override fun navigateToChatActivity(name : String,id : String,bd : String,image : String,consultationId: String) {
        mView?.onClickStartConsultation(name,id,bd,image,consultationId)
    }

    private fun getRequestedGeneralCaseSummary(patientId : String){
        mModel.getPatientGeneralAnswersAndSaveToDatabase(patientId,{
            mView?.showGeneralAnswerData(it)
        },{})
    }

    private fun getRequestedSpecialityCaseSummary(patientId: String){
        mModel.getRequestedPatientCaseSummaryAndSaveToDatabase(patientId,{
            mView?.showSpecialityAnswerData(it)
        },{})
    }
}