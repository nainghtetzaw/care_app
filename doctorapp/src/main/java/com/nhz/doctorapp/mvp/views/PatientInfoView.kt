package com.nhz.doctorapp.mvp.views

import com.nhz.shared.data.vos.CaseSummaryVO
import com.nhz.shared.mvp.views.BaseView

interface PatientInfoView : BaseView {

    fun showGeneralAnswerData(data : List<CaseSummaryVO>)
    fun showSpecialityAnswerData(data: List<CaseSummaryVO>)

}