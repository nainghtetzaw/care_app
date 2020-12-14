package com.nhz.doctorapp.views.viewholders

import android.view.View
import com.nhz.shared.data.vos.CaseSummaryVO

class ChatGeneralInfoViewHolder(itemview : View) : BaseViewHolder<CaseSummaryVO>(itemview) {

    override fun bindData(data: CaseSummaryVO) {
        mData = data
    }
}