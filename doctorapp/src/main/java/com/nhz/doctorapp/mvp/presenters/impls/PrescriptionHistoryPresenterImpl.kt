package com.nhz.doctorapp.mvp.presenters.impls

import com.nhz.doctorapp.mvp.presenters.interfaces.PrescriptionHistoryPresenter
import com.nhz.doctorapp.mvp.views.PrescriptionHistoryView
import com.nhz.shared.mvp.presenters.AbstractBasePresenter

class PrescriptionHistoryPresenterImpl : AbstractBasePresenter<PrescriptionHistoryView>(),PrescriptionHistoryPresenter {
    override fun onUiReady(consultationId: String) {
        mModel.getConsultationPrescriptionAndSaveToDatabase(consultationId,{
            mView?.showPrescriptionData(it)
        },{})
    }
}