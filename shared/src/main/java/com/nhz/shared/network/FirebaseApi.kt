package com.nhz.shared.network

import com.nhz.shared.data.vos.*

interface FirebaseApi {
    fun getSpecialitiesList(onSuccess : (specialityList : List<SpecialitiesVO>) -> Unit, onFailure : (message : String) -> Unit)
    fun getSpecialitiesQuestionsById(specialityId : Int,onSuccess : (questions : List<SpecialityQuestionsVO>) -> Unit,onFailure: (message: String) -> Unit)
    fun getSpecialitiesMedicinesById(specialityId: Int,onSuccess : (medicines : List<MedicinesVO>) -> Unit,onFailure: (message: String) -> Unit)
    fun getGeneralQuestionList(onSuccess : (questionList : List<GeneralQuestionsVO>) -> Unit, onFailure: (message: String) -> Unit)
    fun getAlwaysGeneralQuestionsList(onSuccess: (questionList: List<GeneralQuestionsVO>) -> Unit,onFailure: (message: String) -> Unit)
    fun getOneTimeGeneralQuestionsList(onSuccess: (questionList: List<GeneralQuestionsVO>) -> Unit,onFailure: (message: String) -> Unit)
    fun getDoctorByDoctorId(doctorId : String, onSuccess : (doctor : DoctorVO) -> Unit, onFailure: (message: String) -> Unit)
    fun getPatientByPatientId(patientId : String, onSuccess : (patient : PatientVO) -> Unit, onFailure: (message: String) -> Unit)
    fun getPatientGeneralAnswers(userId : String, onSuccess : (answers : List<CaseSummaryVO>) -> Unit,onFailure: (message: String) -> Unit)
    fun getConsultationRequests(specialityId : Int, onSuccess : (requests : List<ConsultationRequestVO>) -> Unit, onFailure: (message: String) -> Unit)
    fun getRequestedPatientsCaseSummary(id : String, onSuccess : (case : List<CaseSummaryVO>) -> Unit, onFailure: (message: String) -> Unit)
    fun getUnfinishedConsultation(userId: String,finished : Boolean, onSuccess : (consultation : List<ConsultationsVO>) -> Unit, onFailure: (message: String) -> Unit)
    fun getFinishedConsultationsByDoctorId(doctorId : String,onSuccess : (consultation : List<ConsultationsVO>) -> Unit, onFailure: (message: String) -> Unit)
    fun getFinishedConsultationsByPatientId (patientId : String,onSuccess: (consultation: List<ConsultationsVO>) -> Unit,onFailure: (message: String) -> Unit)
    fun getConsultationPrescription(messageId : String, medicineName : String, onSuccess: (prescription : List<PrescriptionVO>) -> Unit, onFailure: (message: String) -> Unit)
    fun getConsultationCaseSummary(messageId: String, onSuccess: (case: List<CaseSummaryVO>) -> Unit, onFailure: (message: String) -> Unit)
    fun getMessage(messageId : String, onSuccess : (messages : List<LiveChatVO>) -> Unit, onFailure: (message: String) -> Unit)
    fun getRecentDoctors(userId : String, onSuccess: (doctors : List<DoctorVO>) -> Unit,onFailure: (message: String) -> Unit)
    fun getCheckOut(userId : String, onSuccess : (checkout : CheckOutVO) -> Unit,onFailure: (message: String) -> Unit)
    fun getCheckOutPrescription(userId: String, onSuccess : (prescription : List<PrescriptionVO>) -> Unit, onFailure: (message: String) -> Unit)

    fun sendMessage(messageId : String, message : LiveChatVO)
    fun prescribeMedicine(messageId : String,prescription : PrescriptionVO)
    fun addNewDoctor(doctor : DoctorVO)
    fun addNewPatient(patient : PatientVO)
    fun addRecentDoctor(userId : String, doctor : DoctorVO)
    fun addConsultation(consultation : ConsultationsVO)
    fun addConsultationCaseSummary(messageId : String , case : CaseSummaryVO)
    fun addConsultationPrescription(messageId : String, prescription: PrescriptionVO)
    fun addPatientGeneralAnswers(userId : String, answers : CaseSummaryVO)
    fun checkOut(userId : String, checkout : CheckOutVO)
    fun checkOutPrescription(userId: String, prescription : PrescriptionVO)
    fun sendConsultationRequest(id : String, consultationRequest : ConsultationRequestVO)
    fun sendRequestedPatientCaseSummary(id: String, case : CaseSummaryVO)

    fun deleteConsultationRequest(id : String)
}