package com.nhz.doctorapp.mvp.presenters.impls

import com.nhz.doctorapp.mvp.presenters.MedicalHistoryPresenter
import com.nhz.doctorapp.mvp.views.MedicalHistoryView
import com.nhz.shared.data.vos.MedicalHistoryVO
import com.nhz.shared.mvp.presenters.AbstractBasePresenter

class MedicalHistoryPresenterImpl : AbstractBasePresenter<MedicalHistoryView>(),MedicalHistoryPresenter {
    override fun onUiReady(name : String,bd : String,consultationId : String) {
        mModel.getConsultationMedicalHistoryFromNetwork(consultationId,{
            mView?.showMedicalHistoryData(name,bd,it)
        },{})
    }
}