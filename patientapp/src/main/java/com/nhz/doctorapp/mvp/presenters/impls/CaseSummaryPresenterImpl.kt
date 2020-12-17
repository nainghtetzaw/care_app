package com.nhz.doctorapp.mvp.presenters.impls

import com.nhz.doctorapp.mvp.presenters.CaseSummaryPresenter
import com.nhz.doctorapp.mvp.views.CaseSummaryView
import com.nhz.shared.mvp.presenters.AbstractBasePresenter

class CaseSummaryPresenterImpl : AbstractBasePresenter<CaseSummaryView>(),CaseSummaryPresenter {
    override fun onUiReady(consultationId: String) {
        mModel.getPatientGeneralAnswersAndSaveToDatabase("72JXNg3bVUZ0FRyanMNiNm2WLPn1",{
            mView?.showGeneralInfo(it)
        },{})
        mModel.getConsultationCaseSummaryAndSaveToDatabase(consultationId,{
            mView?.showSpecialityInfo(it)
        },{})
    }
}