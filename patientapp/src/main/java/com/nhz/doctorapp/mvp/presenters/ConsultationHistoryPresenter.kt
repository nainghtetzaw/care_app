package com.nhz.doctorapp.mvp.presenters

import android.content.Context
import com.nhz.doctorapp.delegates.ConsultationHistoryDelegate
import com.nhz.doctorapp.mvp.views.ConsultationHistoryView
import com.nhz.shared.mvp.presenters.BasePresenter

interface ConsultationHistoryPresenter : BasePresenter<ConsultationHistoryView>,ConsultationHistoryDelegate {

    fun onUiReady(context: Context)

}