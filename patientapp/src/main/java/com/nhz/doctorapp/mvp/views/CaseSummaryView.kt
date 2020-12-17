package com.nhz.doctorapp.mvp.views

import com.nhz.shared.data.vos.CaseSummaryVO
import com.nhz.shared.mvp.views.BaseView

interface CaseSummaryView : BaseView {

    fun showGeneralInfo(data : List<CaseSummaryVO>)
    fun showSpecialityInfo(data: List<CaseSummaryVO>)

}