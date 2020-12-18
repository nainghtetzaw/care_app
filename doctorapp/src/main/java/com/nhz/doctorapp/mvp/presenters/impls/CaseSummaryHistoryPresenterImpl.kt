package com.nhz.doctorapp.mvp.presenters.impls

import com.nhz.doctorapp.mvp.presenters.interfaces.CaseSummaryHistoryPresenter
import com.nhz.doctorapp.mvp.views.CaseSummaryHistoryView
import com.nhz.shared.mvp.presenters.AbstractBasePresenter

class CaseSummaryHistoryPresenterImpl : AbstractBasePresenter<CaseSummaryHistoryView>(),CaseSummaryHistoryPresenter {
    override fun onUiReady(consultationId: String,patientId : String) {
        mModel.getConsultationCaseSummaryAndSaveToDatabase(consultationId,{
            mView?.showSpecialityData(it)
        },{})
        mModel.getPatientGeneralAnswersAndSaveToDatabase(patientId,{
            mView?.showGeneralData(it)
        },{})
    }
}