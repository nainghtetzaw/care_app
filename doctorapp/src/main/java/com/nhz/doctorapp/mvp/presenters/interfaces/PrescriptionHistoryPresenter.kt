package com.nhz.doctorapp.mvp.presenters.interfaces

import com.nhz.doctorapp.mvp.views.PrescriptionHistoryView
import com.nhz.doctorapp.mvp.views.PrescriptionMedicinesView
import com.nhz.shared.mvp.presenters.BasePresenter

interface PrescriptionHistoryPresenter : BasePresenter<PrescriptionHistoryView> {

    fun onUiReady(consultationId : String)

}