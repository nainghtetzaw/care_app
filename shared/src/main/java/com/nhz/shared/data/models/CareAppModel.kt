package com.nhz.shared.data.models

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import com.nhz.shared.data.vos.*

interface CareAppModel {

    fun getSpecialitiesListFromDatabase() : LiveData<List<SpecialitiesVO>>
    fun getSpecialitiesQuestionsFromDatabase() : LiveData<List<SpecialityQuestionsVO>>
    fun getSpecialitiesMedicinesFromDatabase() : LiveData<List<MedicinesVO>>
    fun getGeneralQuestionListFromDatabase() : LiveData<List<GeneralQuestionsVO>>
    fun getGeneralQuestionListFromDatabaseByBoolean(oneTime : Boolean) : LiveData<List<GeneralQuestionsVO>>
    fun getDoctorInfoFromDatabase() : LiveData<DoctorVO>
    fun getPatientInfoFromDatabase() : LiveData<PatientVO>
    fun getConsultationRequestedPatientFromDatabase() : LiveData<List<ConsultationRequestVO>>
    fun getConsultationFromDatabase() : LiveData<List<ConsultationsVO>>
    fun getConsultationPrescriptionFromDatabase() : LiveData<List<PrescriptionVO>>
    fun getConsultationCaseSummaryFromDatabase() : LiveData<List<CaseSummaryVO>>
    fun getRecentDoctorsFromDatabase() : LiveData<List<DoctorVO>>
    fun getPatientGeneralAnswersFromDatabase() : LiveData<List<CaseSummaryVO>>
    fun getPatientGeneralAnswersFromDatabaseByBoolean(oneTime : Boolean) : LiveData<List<CaseSummaryVO>>
    fun getRequestedPatientCaseSummaryFromDatabase() : LiveData<List<CaseSummaryVO>>

    fun getAllSpecialitiesAndSaveToDatabase(onSuccess : (specialityList : List<SpecialitiesVO>) -> Unit, onFailure : (message : String) -> Unit)
    fun getAllSpecialityQuestionsByIdAndSaveToDatabase(specialityId : Int,onSuccess : (questions : List<SpecialityQuestionsVO>) -> Unit,onFailure: (message: String) -> Unit)
    fun getAllSpecialityMedicinesByIdAndSaveToDatabase(specialityId: Int,onSuccess : (medicines : List<MedicinesVO>) -> Unit,onFailure: (message: String) -> Unit)
    fun getAllGeneralQuestionsListAndSaveToDatabase(onSuccess : (questionList : List<GeneralQuestionsVO>) -> Unit, onFailure: (message: String) -> Unit)
    fun getDoctorByDoctorIdAndSaveToDatabase(doctorId : String, onSuccess : (doctor : DoctorVO) -> Unit, onFailure: (message: String) -> Unit)
    fun getPatientByPatientIdAndSaveToDatabase(patientId : String, onSuccess : (patient : PatientVO) -> Unit, onFailure: (message: String) -> Unit)
    fun getConsultationRequestedPatientAndSaveToDatabase(specialityId : Int , onSuccess : (requests : List<ConsultationRequestVO>) -> Unit, onFailure: (message: String) -> Unit)
    fun getFinishedConsultationsByDoctorIdAndSaveToDatabase(doctorId : String,onSuccess : (consultation : List<ConsultationsVO>) -> Unit, onFailure: (message: String) -> Unit)
    fun getFinishedConsultationsByPatientIdAndSaveToDatabase(patientId : String,onSuccess: (consultation: List<ConsultationsVO>) -> Unit,onFailure: (message: String) -> Unit)
    fun getConsultationPrescriptionAndSaveToDatabase(messageId : String, onSuccess: (prescription : List<PrescriptionVO>) -> Unit, onFailure: (message: String) -> Unit)
    fun getConsultationCaseSummaryAndSaveToDatabase(messageId: String, onSuccess: (case: List<CaseSummaryVO>) -> Unit, onFailure: (message: String) -> Unit)
    fun getRecentDoctorsAndSaveToDatabase(id : String, onSuccess: (doctors : List<DoctorVO>) -> Unit, onFailure: (message: String) -> Unit)
    fun getPatientGeneralAnswersAndSaveToDatabase(userId : String, onSuccess : (answers : List<CaseSummaryVO>) -> Unit,onFailure: (message: String) -> Unit)
    fun getRequestedPatientCaseSummaryAndSaveToDatabase(id : String, onSuccess : (case : List<CaseSummaryVO>) -> Unit, onFailure: (message: String) -> Unit)

    fun getOneTimeGeneralQuestionsFromNetwork(onSuccess: (questionList: List<GeneralQuestionsVO>) -> Unit, onFailure: (message: String) -> Unit)
    fun getAlwaysGeneralQuestionsFromNetwork(onSuccess: (questionList: List<GeneralQuestionsVO>) -> Unit, onFailure: (message: String) -> Unit)
    fun getUnfinishedConsultationFromNetworkByDoctorId(userId : String,finished : Boolean, onSuccess : (consultation : List<ConsultationsVO>) -> Unit, onFailure: (message: String) -> Unit)
    fun getUnfinishedConsultationFromNetworkByPatientId(userId : String,finished : Boolean, onSuccess : (consultation : List<ConsultationsVO>) -> Unit, onFailure: (message: String) -> Unit)
    fun getMessageFromNetwork(messageId : String, onSuccess : (messages : List<LiveChatVO>) -> Unit, onFailure: (message: String) -> Unit)
    fun getCheckOutFromNetwork(userId : String, onSuccess : (checkout : CheckOutVO) -> Unit,onFailure: (message: String) -> Unit)
    fun getCheckOutPrescriptionFromNetwork(userId: String, onSuccess : (prescription : List<PrescriptionVO>) -> Unit, onFailure: (message: String) -> Unit)
    fun getConsultationMedicalHistoryFromNetwork(messageId : String,onSuccess : (history : MedicalHistoryVO) -> Unit,onFailure: (message: String) -> Unit)
    fun getConsultationByIdFromNetwork(id: String, onSuccess: (consultation: ConsultationsVO) -> Unit, onFailure: (message: String) -> Unit)

    fun sendMessage(messageId : String, message : LiveChatVO)
    fun prescribeMedicine(messageId : String,prescription : PrescriptionVO)
    fun addNewDoctor(doctor : DoctorVO)
    fun addNewPatient(patient : PatientVO)
    fun addPatientGeneralAnswers(userId : String, questions : CaseSummaryVO)
    fun addRecentDoctors(userId : String, doctor : DoctorVO)
    fun addConsultation(consultation : ConsultationsVO)
    fun addConsultationCaseSummary(messageId : String , case : CaseSummaryVO)
    fun addConsultationPrescription(messageId : String, prescription: PrescriptionVO)
    fun checkOut(userId : String, checkout : CheckOutVO)
    fun checkOutPrescription(userId: String, prescription : PrescriptionVO)
    fun sendConsultationRequestPatient(id : String,consultationRequest : ConsultationRequestVO)
    fun sendRequestedPatientCaseSummary(id : String, case : CaseSummaryVO)
    fun addConsultationMedicalHistory(messageId : String,history : MedicalHistoryVO)
    fun uploadImage(bitmap : Bitmap,onSuccess : (image : String) -> Unit,onFailure: (message: String) -> Unit)

    fun deleteConsultationRequest(id : String)
    fun deleteMedicine(name : String,consultationId : String)
//    fun deleteCheckOutPrescription(userId : String)
}