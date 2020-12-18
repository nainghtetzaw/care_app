package com.nhz.doctorapp.mvp.views

import com.nhz.shared.data.vos.CaseSummaryVO
import com.nhz.shared.mvp.views.BaseView

interface CaseSummaryHistoryView  : BaseView {
    fun showGeneralData(data : List<CaseSummaryVO>)
    fun showSpecialityData(data : List<CaseSummaryVO>)
}