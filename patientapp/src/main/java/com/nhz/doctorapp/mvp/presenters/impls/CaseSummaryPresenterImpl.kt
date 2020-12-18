package com.nhz.doctorapp.mvp.presenters.impls

import com.nhz.doctorapp.mvp.presenters.CaseSummaryPresenter
import com.nhz.doctorapp.mvp.views.CaseSummaryView
import com.nhz.shared.mvp.presenters.AbstractBasePresenter

class CaseSummaryPresenterImpl : AbstractBasePresenter<CaseSummaryView>(),CaseSummaryPresenter {
    override fun onUiReady(consultationId: String) {
        mModel.getPatientGeneralAnswersAndSaveToDatabase(mAuthModel.getUserToken(),{
            mView?.showGeneralInfo(it)
        },{})
        mModel.getConsultationCaseSummaryAndSaveToDatabase(consultationId,{
            mView?.showSpecialityInfo(it)
        },{})
    }
}