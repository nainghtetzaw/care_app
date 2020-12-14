package com.nhz.shared.data.models

import androidx.lifecycle.LiveData
import com.nhz.shared.data.vos.*

object CareAppModelImpl : BaseModel(),CareAppModel {
    override fun getSpecialitiesListFromDatabase(): LiveData<List<SpecialitiesVO>> {
        return mDatabase.specialitiesDao().getSpecialities()
    }

    override fun getSpecialitiesQuestionsFromDatabase(): LiveData<List<SpecialityQuestionsVO>> {
        return mDatabase.specialityQuestions().getSpecialityQuestions()
    }

    override fun getSpecialitiesMedicinesFromDatabase(): LiveData<List<MedicinesVO>> {
        return mDatabase.medicinesDao().getMedicines()
    }

    override fun getGeneralQuestionListFromDatabase(): LiveData<List<GeneralQuestionsVO>> {
        return mDatabase.generalQuestionsDao().getGeneralQuestions()
    }

    override fun getGeneralQuestionListFromDatabaseByBoolean(oneTime: Boolean): LiveData<List<GeneralQuestionsVO>> {
        return mDatabase.generalQuestionsDao().getGeneralQuestionsByBoolean(oneTime)
    }

    override fun getDoctorInfoFromDatabase(): LiveData<DoctorVO> {
        return mDatabase.doctorDao().getDoctorInfo()
    }

    override fun getPatientInfoFromDatabase(): LiveData<PatientVO> {
        return mDatabase.patientDao().getPatientInfo()
    }

    override fun getConsultationRequestedPatientFromDatabase(): LiveData<List<ConsultationRequestVO>> {
        return mDatabase.consultationRequestedPatientDao().getRequestedPatientInfo()
    }

    override fun getConsultationFromDatabase(): LiveData<List<ConsultationsVO>> {
        return mDatabase.consultationDao().getConsultations()
    }

    override fun getConsultationPrescriptionFromDatabase(): LiveData<List<PrescriptionVO>> {
        return mDatabase.consultationPrescriptionDao().getAllPrescription()
    }

    override fun getConsultationCaseSummaryFromDatabase(): LiveData<List<CaseSummaryVO>> {
        return mDatabase.consultationCaseSummaryDao().getAllCaseSummary()
    }

    override fun getRecentDoctorsFromDatabase(): LiveData<List<DoctorVO>> {
        return mDatabase.recentDoctorDao().getDoctorInfo()
    }

    override fun getPatientGeneralAnswersFromDatabase(): LiveData<List<CaseSummaryVO>> {
        return mDatabase.patientGeneralAnswersDao().getAllCaseSummary()
    }

    override fun getPatientGeneralAnswersFromDatabaseByBoolean(oneTime: Boolean): LiveData<List<CaseSummaryVO>> {
        return mDatabase.patientGeneralAnswersDao().getCaseSummaryByBoolean(oneTime)
    }

    override fun getRequestedPatientCaseSummaryFromDatabase(): LiveData<List<CaseSummaryVO>> {
        return mDatabase.requestedPatientCaseSummaryDao().getRequestedPatientCaseSummary()
    }

    override fun getAllSpecialitiesAndSaveToDatabase(
        onSuccess: (specialityList: List<SpecialitiesVO>) -> Unit,
        onFailure: (message: String) -> Unit
    ) {
        mFirebaseApi.getSpecialitiesList({
            onSuccess(it)
            mDatabase.specialitiesDao().insertIntoSpecialities(it)
        },onFailure)
    }

    override fun getAllSpecialityQuestionsByIdAndSaveToDatabase(
        specialityId: Int,
        onSuccess: (questions: List<SpecialityQuestionsVO>) -> Unit,
        onFailure: (message: String) -> Unit
    ) {
        mFirebaseApi.getSpecialitiesQuestionsById(specialityId,{
            onSuccess(it)
            mDatabase.specialityQuestions().insertIntoSpecialityQuestions(it)
        },onFailure)
    }

    override fun getAllSpecialityMedicinesByIdAndSaveToDatabase(
        specialityId: Int,
        onSuccess: (medicines: List<MedicinesVO>) -> Unit,
        onFailure: (message: String) -> Unit
    ) {
        mFirebaseApi.getSpecialitiesMedicinesById(specialityId,{
            onSuccess(it)
            mDatabase.medicinesDao().insertMedicines(it)
        },onFailure)
    }

    override fun getAllGeneralQuestionsListAndSaveToDatabase(
        onSuccess: (questionList: List<GeneralQuestionsVO>) -> Unit,
        onFailure: (message: String) -> Unit
    ) {
        mFirebaseApi.getGeneralQuestionList({
            onSuccess(it)
            mDatabase.generalQuestionsDao().insertGeneralQuestions(it)
        },onFailure)
    }

    override fun getDoctorByDoctorIdAndSaveToDatabase(
        doctorId: String,
        onSuccess: (doctor: DoctorVO) -> Unit,
        onFailure: (message: String) -> Unit
    ) {
        mFirebaseApi.getDoctorByDoctorId(doctorId,{
            onSuccess(it)
            mDatabase.doctorDao().insertDoctor(it)
        },onFailure)
    }

    override fun getPatientByPatientIdAndSaveToDatabase(
        patientId: String,
        onSuccess: (patient: PatientVO) -> Unit,
        onFailure: (message: String) -> Unit
    ) {
        mFirebaseApi.getPatientByPatientId(patientId,{
            onSuccess(it)
            mDatabase.patientDao().insertPatientInfo(it)
        },onFailure)
    }

    override fun getConsultationRequestedPatientAndSaveToDatabase(
        specialityId: Int,
        onSuccess: (requests : List<ConsultationRequestVO>) -> Unit,
        onFailure: (message: String) -> Unit
    ) {
        mFirebaseApi.getConsultationRequests(specialityId,{
            onSuccess(it)
            mDatabase.consultationRequestedPatientDao().insertInfoRequestedPatientInfo(it)
        },onFailure)
    }

    override fun getFinishedConsultationsByDoctorIdAndSaveToDatabase(doctorId : String,onSuccess: (consultation: List<ConsultationsVO>) -> Unit, onFailure: (message: String) -> Unit) {
        mFirebaseApi.getFinishedConsultationsByDoctorId(doctorId ,{
            onSuccess(it)
            mDatabase.consultationDao().insertConsultation(it)
        },onFailure)
    }

    override fun getFinishedConsultationsByPatientIdAndSaveToDatabase(
        patientId: String,
        onSuccess: (consultation: List<ConsultationsVO>) -> Unit,
        onFailure: (message: String) -> Unit
    ) {
        mFirebaseApi.getFinishedConsultationsByPatientId(patientId,{
            mDatabase.consultationDao().insertConsultation(it)
        },onFailure)
    }

    override fun getConsultationPrescriptionAndSaveToDatabase(
        messageId: String,
        medicineName: String,
        onSuccess: (prescription: List<PrescriptionVO>) -> Unit,
        onFailure: (message: String) -> Unit
    ) {
        mFirebaseApi.getConsultationPrescription(messageId,medicineName,{
            onSuccess(it)
            mDatabase.consultationPrescriptionDao().insertIntoConsultationPrescription(it)
        },onFailure)
    }

    override fun getConsultationCaseSummaryAndSaveToDatabase(
        messageId: String,
        onSuccess: (case: List<CaseSummaryVO>) -> Unit,
        onFailure: (message: String) -> Unit
    ) {
        mFirebaseApi.getConsultationCaseSummary(messageId,{
            onSuccess(it)
            mDatabase.consultationCaseSummaryDao().insertCaseSummaryData(it)
        },onFailure)
    }

    override fun getRecentDoctorsAndSaveToDatabase(
            id: String,
            onSuccess: (doctors: List<DoctorVO>) -> Unit,
            onFailure: (message: String) -> Unit
    ) {
        mFirebaseApi.getRecentDoctors(id,{
            onSuccess(it)
            mDatabase.recentDoctorDao().insertDoctor(it)
        },onFailure)
    }

    override fun getPatientGeneralAnswersAndSaveToDatabase(
        userId: String,
        onSuccess: (answers: List<CaseSummaryVO>) -> Unit,
        onFailure: (message: String) -> Unit
    ) {
        mFirebaseApi.getPatientGeneralAnswers(userId,{
            onSuccess(it)
            mDatabase.patientGeneralAnswersDao().insertCaseSummaryData(it)
        },onFailure)
    }

    override fun getUnfinishedConsultationFromNetwork(userId : String,finished : Boolean, onSuccess: (consultation: List<ConsultationsVO>) -> Unit, onFailure: (message: String) -> Unit) {
        mFirebaseApi.getUnfinishedConsultation(userId ,finished ,onSuccess,onFailure)
    }

    override fun getRequestedPatientCaseSummaryAndSaveToDatabase(
        id : String,
        onSuccess: (case: List<CaseSummaryVO>) -> Unit,
        onFailure: (message: String) -> Unit
    ) {
        mFirebaseApi.getRequestedPatientsCaseSummary(id,{
            onSuccess(it)
            mDatabase.requestedPatientCaseSummaryDao().insertIntoRequestedPatientCaseSummary(it)
        },onFailure)
    }

    override fun getOneTimeGeneralQuestionsFromNetwork(onSuccess: (questionList: List<GeneralQuestionsVO>) -> Unit, onFailure: (message: String) -> Unit) {
        mFirebaseApi.getOneTimeGeneralQuestionsList(onSuccess,onFailure)
    }

    override fun getAlwaysGeneralQuestionsFromNetwork(onSuccess: (questionList: List<GeneralQuestionsVO>) -> Unit, onFailure: (message: String) -> Unit) {
        mFirebaseApi.getAlwaysGeneralQuestionsList(onSuccess,onFailure)
    }

    override fun getMessageFromNetwork(
        messageId: String,
        onSuccess: (messages: List<LiveChatVO>) -> Unit,
        onFailure: (message: String) -> Unit
    ) {
        mFirebaseApi.getMessage(messageId,onSuccess,onFailure)
    }

    override fun getCheckOutFromNetwork(
        userId: String,
        onSuccess: (checkout: CheckOutVO) -> Unit,
        onFailure: (message: String) -> Unit
    ) {
        mFirebaseApi.getCheckOut(userId,onSuccess,onFailure)
    }

    override fun getCheckOutPrescriptionFromNetwork(
        userId: String,
        onSuccess: (prescription: List<PrescriptionVO>) -> Unit,
        onFailure: (message: String) -> Unit
    ) {
        mFirebaseApi.getCheckOutPrescription(userId,onSuccess, onFailure)
    }

    override fun sendMessage(messageId: String, message: LiveChatVO) {
        mFirebaseApi.sendMessage(messageId,message)
    }

    override fun prescribeMedicine(messageId: String, prescription: PrescriptionVO) {
        mFirebaseApi.prescribeMedicine(messageId,prescription)
    }

    override fun addNewDoctor(doctor: DoctorVO) {
        mFirebaseApi.addNewDoctor(doctor)
    }

    override fun addNewPatient(patient: PatientVO) {
        mFirebaseApi.addNewPatient(patient)
    }

    override fun addPatientGeneralAnswers(userId: String, questions: CaseSummaryVO) {
        mFirebaseApi.addPatientGeneralAnswers(userId,questions)
    }

    override fun addRecentDoctors(userId: String, doctor: DoctorVO) {
        mFirebaseApi.addRecentDoctor(userId, doctor)
    }

    override fun addConsultation(consultation : ConsultationsVO) {
        mFirebaseApi.addConsultation(consultation)
    }

    override fun addConsultationCaseSummary(messageId: String, case: CaseSummaryVO) {
        mFirebaseApi.addConsultationCaseSummary(messageId, case)
    }

    override fun addConsultationPrescription(messageId: String, prescription: PrescriptionVO) {
        mFirebaseApi.addConsultationPrescription(messageId, prescription)
    }

    override fun checkOut(userId: String, checkout: CheckOutVO) {
        mFirebaseApi.checkOut(userId,checkout)
    }

    override fun checkOutPrescription(userId: String, prescription: PrescriptionVO) {
        mFirebaseApi.checkOutPrescription(userId, prescription)
    }

    override fun sendConsultationRequestPatient(id : String,consultationRequest : ConsultationRequestVO) {
        mFirebaseApi.sendConsultationRequest(id,consultationRequest)
    }

    override fun sendRequestedPatientCaseSummary(
        id : String,
        case: CaseSummaryVO,
    ) {
        mFirebaseApi.sendRequestedPatientCaseSummary(id, case)
    }

    override fun deleteConsultationRequest(id: String) {
        mFirebaseApi.deleteConsultationRequest(id)
    }
}