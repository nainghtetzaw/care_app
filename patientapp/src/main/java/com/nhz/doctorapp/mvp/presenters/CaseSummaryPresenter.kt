package com.nhz.doctorapp.mvp.presenters

import com.nhz.doctorapp.mvp.views.CaseSummaryView
import com.nhz.shared.mvp.presenters.BasePresenter

interface CaseSummaryPresenter : BasePresenter<CaseSummaryView> {

    fun onUiReady(consultationId : String)

}