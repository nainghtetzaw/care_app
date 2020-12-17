package com.nhz.doctorapp.mvp.views

import com.nhz.shared.data.vos.MedicalHistoryVO
import com.nhz.shared.mvp.views.BaseView

interface MedicalHistoryView : BaseView {

    fun showMedicalHistoryData(name : String,bd : String,medicalHistory  : MedicalHistoryVO)

}