package com.nhz.doctorapp.mvp.views

import com.nhz.shared.data.vos.CaseSummaryVO
import com.nhz.shared.data.vos.GeneralQuestionsVO
import com.nhz.shared.mvp.views.BaseView

interface GeneralQuestionsView : BaseView {

    fun showOneTimeQuestionsAndAnswers(data : List<CaseSummaryVO>)
    fun showAlwaysQuestions(data : List<GeneralQuestionsVO>)
    fun hideQuestionsAndAnswersList()
    fun showQuestionsAndAnswersList()

}