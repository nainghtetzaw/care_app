package com.nhz.doctorapp.mvp.presenters.impls

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.nhz.doctorapp.mvp.presenters.interfaces.PatientInfoPresenter
import com.nhz.doctorapp.mvp.views.PatientInfoView
import com.nhz.shared.mvp.presenters.AbstractBasePresenter

class PatientInfoPresenterImpl : AbstractBasePresenter<PatientInfoView>(),PatientInfoPresenter {
    override fun onUiReady(patientId: String,consultationId: String,context: Context, lifecycleOwner: LifecycleOwner) {
        getRequestedCaseSummary(patientId,consultationId)
    }

    override fun navigateToChatActivity() {

    }

    private fun getRequestedCaseSummary(patientId : String,consultationId : String){
        mModel.getPatientGeneralAnswersAndSaveToDatabase(patientId,{
            mView?.showGeneralAnswerData(it)
        },{})
        mModel.getRequestedPatientCaseSummaryAndSaveToDatabase(consultationId,{
            mView?.showSpecialityAnswerData(it)
        },{})
    }
}