package com.nhz.doctorapp.mvp.views

import com.nhz.shared.data.vos.*
import com.nhz.shared.mvp.views.BaseView

interface ChatView : BaseView{

    fun showSpecialityQuestionAndAnswerData(data : List<CaseSummaryVO>)
    fun showGeneralQuestionAndAnswerData(data : List<CaseSummaryVO>)
    fun showMessageList(data : List<LiveChatVO>,doctorId : String)

}