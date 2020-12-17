package com.nhz.doctorapp.mvp.presenters.impls

import com.nhz.doctorapp.mvp.presenters.ConsultationPrescriptionPresenter
import com.nhz.doctorapp.mvp.views.ConsultationPrescriptionView
import com.nhz.shared.mvp.presenters.AbstractBasePresenter

class ConsultationPrescriptionPresenterImpl : AbstractBasePresenter<ConsultationPrescriptionView>(),ConsultationPrescriptionPresenter {
    override fun onUiReady(consultationId: String) {
        mModel.getConsultationPrescriptionAndSaveToDatabase(consultationId,{
            mView?.showPrescriptionList(it)
        },{})
    }

}