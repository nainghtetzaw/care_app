package com.nhz.doctorapp.mvp.views

import com.nhz.shared.data.vos.SpecialityQuestionsVO
import com.nhz.shared.mvp.views.BaseView

interface SpecialityQuestionView : BaseView {

    fun showSpecialityQuestions(data : List<SpecialityQuestionsVO>)
    fun onStartConsultation(id : String)

}