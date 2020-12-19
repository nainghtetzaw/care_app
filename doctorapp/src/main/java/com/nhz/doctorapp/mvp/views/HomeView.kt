package com.nhz.doctorapp.mvp.views

import com.nhz.shared.data.vos.ConsultationRequestVO
import com.nhz.shared.data.vos.ConsultationsVO
import com.nhz.shared.data.vos.DoctorVO
import com.nhz.shared.data.vos.PatientVO
import com.nhz.shared.mvp.views.BaseView

interface HomeView : BaseView {

    fun showConsultationRequestData(data : List<ConsultationRequestVO>,doctorId : String)
    fun showConsultationHistoryData(data: List<ConsultationsVO>)
    fun showDoctorInfo(doctor : DoctorVO)
    fun navigateToPatientInfoActivity(patient : PatientVO,id : String)
    fun showMedicalHistoryDialogFragment(consultationId : String,patientName : String,patientBd : String)
    fun showCaseSummaryHistoryDialogFragment(consultationId: String,patientId : String)
    fun showPrescriptionHistoryDialogFragment(consultationId: String)
    fun showSetConsultationTimeFragmentDialog()
    fun navigateToChatActivity(patientName: String,patientId: String,patientBd: String,patientImage : String,consultationId: String)
//    fun showOrHideEmptyView()

    fun showConsultationRequestList()
    fun hideConsultationRequestList()
    fun showConsultationHistoryList()
    fun hideConsultationHistoryList()
    fun showEmpty()
    fun hideEmpty()

}