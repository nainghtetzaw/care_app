package com.nhz.doctorapp.mvp.presenters.interfaces

import com.nhz.doctorapp.mvp.views.CaseSummaryHistoryView
import com.nhz.shared.mvp.presenters.BasePresenter

interface CaseSummaryHistoryPresenter : BasePresenter<CaseSummaryHistoryView> {
    fun onUiReady(consultationId : String,patientId : String)
}