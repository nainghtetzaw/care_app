package com.nhz.doctorapp.mvp.views

import com.nhz.shared.data.vos.CaseSummaryVO
import com.nhz.shared.mvp.views.BaseView

interface ConfirmCaseSummaryView : BaseView {

    fun showGeneralQuestionsAndAnswers(data : List<CaseSummaryVO>)
    fun showSpecialityQuestionsAndAnswers(data : List<CaseSummaryVO>)
    fun onStartRequest(id : String)

}