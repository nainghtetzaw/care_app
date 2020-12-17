package com.nhz.doctorapp.mvp.presenters

import com.nhz.doctorapp.mvp.views.MedicalHistoryView
import com.nhz.shared.data.vos.MedicalHistoryVO
import com.nhz.shared.mvp.presenters.BasePresenter

interface MedicalHistoryPresenter : BasePresenter<MedicalHistoryView> {

    fun onUiReady(name : String,bd : String,consultationId : String)

}