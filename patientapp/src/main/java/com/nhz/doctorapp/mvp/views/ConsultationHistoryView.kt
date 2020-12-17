package com.nhz.doctorapp.mvp.views

import com.nhz.shared.data.vos.ConsultationsVO
import com.nhz.shared.mvp.views.BaseView

interface ConsultationHistoryView : BaseView {

    fun showConsultationHistoryData(data : List<ConsultationsVO>)
    fun showPrescriptionDialogFragment(consultationId : String)
    fun showMedicalHistoryDialogFragment(consultationId: String,patientName : String,patientBd : String)

}