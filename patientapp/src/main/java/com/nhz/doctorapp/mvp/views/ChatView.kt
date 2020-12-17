package com.nhz.doctorapp.mvp.views

import com.nhz.shared.data.vos.CaseSummaryVO
import com.nhz.shared.data.vos.DoctorVO
import com.nhz.shared.data.vos.LiveChatVO
import com.nhz.shared.mvp.views.BaseView

interface ChatView  : BaseView{

    fun showSpecialityQuestionAndAnswerData(data : List<CaseSummaryVO>)
    fun showGeneralQuestionAndAnswerData(data : List<CaseSummaryVO>)
    fun showMessageList(data : List<LiveChatVO>, patientId : String)
    fun setDoctorAndPatientInfo(patientName : String,patientBd : String)
    fun navigateToOrderMedicineActivity(consultationId : String)

}