package com.nhz.doctorapp.mvp.presenters

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.nhz.doctorapp.delegates.GeneralQuestionsDelegate
import com.nhz.doctorapp.mvp.views.GeneralQuestionsView
import com.nhz.shared.data.vos.CaseSummaryVO
import com.nhz.shared.mvp.presenters.BasePresenter

interface GeneralQuestionsPresenter : BasePresenter<GeneralQuestionsView>,GeneralQuestionsDelegate {

    fun onUiReady(context: Context,lifecycleOwner: LifecycleOwner)
    fun sendGeneralAnswers(data : List<CaseSummaryVO>)
}