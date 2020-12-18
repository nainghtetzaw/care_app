package com.nhz.doctorapp.mvp.presenters.impls

import com.nhz.doctorapp.mvp.presenters.interfaces.MedicalHistoryPresenter
import com.nhz.doctorapp.mvp.views.MedicalHistoryView
import com.nhz.shared.data.vos.MedicalHistoryVO
import com.nhz.shared.mvp.presenters.AbstractBasePresenter

class MedicalHistoryPresenterImpl : AbstractBasePresenter<MedicalHistoryView>(),MedicalHistoryPresenter {
    override fun addMedicalHistory(consultationId : String,date : String,note : String) {
        mModel.addConsultationMedicalHistory(consultationId, MedicalHistoryVO(date_of_consultation = date,note = note))
    }

    override fun onUiReady(consultationId: String) {
        mModel.getConsultationMedicalHistoryFromNetwork(consultationId,{
            mView?.showMedicalData(it)
        },{})
    }
}