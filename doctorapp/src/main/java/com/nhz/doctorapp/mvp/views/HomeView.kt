package com.nhz.doctorapp.mvp.views

import com.nhz.shared.data.vos.ConsultationRequestVO
import com.nhz.shared.data.vos.ConsultationsVO
import com.nhz.shared.data.vos.DoctorVO
import com.nhz.shared.data.vos.PatientVO
import com.nhz.shared.mvp.views.BaseView

interface HomeView : BaseView {

    fun showConsultationRequestData(data : List<ConsultationRequestVO>)
    fun showConsultationHistoryData(data: List<ConsultationsVO>)
    fun showDoctorInfo(doctor : DoctorVO)
    fun navigateToPatientInfoActivity(patient : PatientVO,id : String)

    fun showConsultationRequestList()
    fun hideConsultationRequestList()
    fun showConsultationHistoryList()
    fun hideConsultationHistoryList()

}