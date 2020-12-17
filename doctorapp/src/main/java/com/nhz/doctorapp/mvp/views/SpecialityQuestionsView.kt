package com.nhz.doctorapp.mvp.views

import com.nhz.shared.data.vos.SpecialityQuestionsVO
import com.nhz.shared.mvp.views.BaseView

interface SpecialityQuestionsView : BaseView {

    fun showQuestions(data : List<SpecialityQuestionsVO>)

}