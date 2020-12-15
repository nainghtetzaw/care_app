package com.nhz.doctorapp.mvp.presenters

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.nhz.doctorapp.mvp.views.SpecialityQuestionView
import com.nhz.shared.data.vos.CaseSummaryVO
import com.nhz.shared.mvp.presenters.BasePresenter

interface SpecialityQuestionsPresenter : BasePresenter<SpecialityQuestionView>{

    fun onUiReady(id : Int,oldOrNew : Boolean,context: Context,lifecycleOwner: LifecycleOwner)
    fun showConfirmDialogAndSendAnswersToNetwork(data : List<CaseSummaryVO>)
    fun createConsultationRequest(id : Int,oldOrNew: Boolean,lifecycleOwner: LifecycleOwner)

}