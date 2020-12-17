package com.nhz.doctorapp.mvp.presenters.impls

import com.nhz.doctorapp.mvp.presenters.interfaces.PrescribePresenter
import com.nhz.doctorapp.mvp.views.PrescribeView
import com.nhz.shared.data.vos.PrescriptionVO
import com.nhz.shared.mvp.presenters.AbstractBasePresenter

class PrescribePresenterImpl : AbstractBasePresenter<PrescribeView>(),PrescribePresenter {
    override fun onClickAdd(consultationId : String,prescription : PrescriptionVO) {
        mModel.addConsultationPrescription(consultationId,prescription)
    }
}