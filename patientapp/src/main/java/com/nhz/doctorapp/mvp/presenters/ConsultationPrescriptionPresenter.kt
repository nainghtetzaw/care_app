package com.nhz.doctorapp.mvp.presenters

import com.nhz.doctorapp.mvp.views.ConsultationPrescriptionView
import com.nhz.shared.mvp.presenters.BasePresenter

interface ConsultationPrescriptionPresenter : BasePresenter<ConsultationPrescriptionView> {

    fun onUiReady(consultationId : String)

}