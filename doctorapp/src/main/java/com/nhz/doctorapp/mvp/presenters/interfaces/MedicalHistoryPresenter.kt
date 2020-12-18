package com.nhz.doctorapp.mvp.presenters.interfaces

import com.nhz.doctorapp.mvp.views.MedicalHistoryView
import com.nhz.shared.mvp.presenters.BasePresenter

interface MedicalHistoryPresenter : BasePresenter<MedicalHistoryView> {

    fun addMedicalHistory(consultationId : String,date : String,note : String)
    fun onUiReady(consultationId: String)

}