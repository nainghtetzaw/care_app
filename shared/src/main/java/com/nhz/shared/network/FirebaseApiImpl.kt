package com.nhz.shared.network

import android.graphics.Bitmap
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nhz.shared.*
import com.nhz.shared.data.vos.*
import java.io.ByteArrayOutputStream
import java.util.*

object FirebaseApiImpl : FirebaseApi {

    private val db = Firebase.firestore
    private val storage = FirebaseStorage.getInstance()
    private val storageReferance = storage.reference

    override fun getSpecialitiesList(
            onSuccess: (specilityList: List<SpecialitiesVO>) -> Unit,
            onFailure: (message: String) -> Unit
    ) {
        db.collection(specialities)
                .addSnapshotListener { value, error ->
                    error?.let {
                        onFailure(it.message ?: "Please check your internet connection.")
                    } ?: kotlin.run {
                        val specialityList : MutableList<SpecialitiesVO> = mutableListOf()
                        val result = value?.documents ?: arrayListOf()

                        for (document in result) {
                            val data = document.data
                            val dataStr = Gson().toJson(data)
                            val dataType = object : TypeToken<SpecialitiesVO>(){}.type
                            specialityList.add(Gson().fromJson(dataStr,dataType))
                        }
                        onSuccess(specialityList)
                    }
                }
    }

    override fun getSpecialitiesQuestionsById(
        specialityId: Int,
        onSuccess: (questions: List<SpecialityQuestionsVO>) -> Unit,
        onFailure: (message: String) -> Unit
    ) {
        db.collection(specialities).document(specialityId.toString()).collection(questions)
            .addSnapshotListener { value, error ->
                error?.let {
                    onFailure(it.message ?: "Please check your internet connection.")
                } ?: kotlin.run {
                    val questionList : MutableList<SpecialityQuestionsVO> = mutableListOf()
                    val request = value?.documents ?: arrayListOf()

                    for(documents in request){
                        val data = documents.data
                        val dataStr = Gson().toJson(data)
                        val dataType = object : TypeToken<SpecialityQuestionsVO>(){}.type
                        questionList.add(Gson().fromJson(dataStr,dataType))
                    }
                    onSuccess(questionList)
                }
            }
    }

    override fun getSpecialitiesMedicinesById(specialityId: Int, onSuccess: (medicines: List<MedicinesVO>) -> Unit, onFailure: (message: String) -> Unit) {
        db.collection(specialities).document(specialityId.toString()).collection(medicines)
                .addSnapshotListener { value, error ->
                    error?.let {
                        onFailure(it.message ?: "Please check your internet connection.")
                    } ?: kotlin.run {
                        val medicineList : MutableList<MedicinesVO> = mutableListOf()
                        val request = value?.documents ?: arrayListOf()

                        for(document in request){
                            val data = document.data
                            val dataStr = Gson().toJson(data)
                            val dataType = object : TypeToken<MedicinesVO>(){}.type

                            medicineList.add(Gson().fromJson(dataStr,dataType))
                        }
                        onSuccess(medicineList)
                    }
                }
    }

    override fun getGeneralQuestionList(
            onSuccess: (questionList: List<GeneralQuestionsVO>) -> Unit,
            onFailure: (message: String) -> Unit
    ) {
        db.collection(general_questions)
                .addSnapshotListener { value, error ->
                    error?.let {
                        onFailure(it.message ?: "Please check your internet connection.")
                    } ?: kotlin.run {
                        val questionsList : MutableList<GeneralQuestionsVO> = mutableListOf()
                        val result = value?.documents ?: arrayListOf()

                        for (document in result) {
                            val data = document.data
                            val dataStr = Gson().toJson(data)
                            val dataType = object : TypeToken<GeneralQuestionsVO>(){}.type

                            questionsList.add(Gson().fromJson(dataStr,dataType))
                        }
                        onSuccess(questionsList)
                    }
                }
    }

    override fun getAlwaysGeneralQuestionsList(onSuccess: (questionList: List<GeneralQuestionsVO>) -> Unit, onFailure: (message: String) -> Unit) {
        db.collection(general_questions).whereEqualTo("one_time",false)
                .addSnapshotListener { value, error ->
                    error?.let {
                        onFailure(it.message ?: "Please check your internet connection.")
                    } ?: kotlin.run {
                        val questionsList : MutableList<GeneralQuestionsVO> = mutableListOf()
                        val result = value?.documents ?: arrayListOf()

                        for (document in result) {
                            val data = document.data
                            val dataStr = Gson().toJson(data)
                            val dataType = object : TypeToken<GeneralQuestionsVO>(){}.type

                            questionsList.add(Gson().fromJson(dataStr,dataType))
                        }
                        onSuccess(questionsList)
                    }
                }
    }

    override fun getOneTimeGeneralQuestionsList(onSuccess: (questionList: List<GeneralQuestionsVO>) -> Unit, onFailure: (message: String) -> Unit) {
        db.collection(general_questions).whereEqualTo("one_time",true)
                .addSnapshotListener { value, error ->
                    error?.let {
                        onFailure(it.message ?: "Please check your internet connection.")
                    } ?: kotlin.run {
                        val questionsList : MutableList<GeneralQuestionsVO> = mutableListOf()
                        val result = value?.documents ?: arrayListOf()

                        for (document in result) {
                            val data = document.data
                            val dataStr = Gson().toJson(data)
                            val dataType = object : TypeToken<GeneralQuestionsVO>(){}.type

                            questionsList.add(Gson().fromJson(dataStr,dataType))
                        }
                        onSuccess(questionsList)
                    }
                }
    }

    override fun getDoctorByDoctorId(
            doctorId: String,
            onSuccess: (doctor: DoctorVO) -> Unit,
            onFailure: (message: String) -> Unit
    ) {
        db.collection(doctors).document(doctorId)
                .addSnapshotListener { value, error ->
                    error?.let {
                        onFailure(it.message ?: "Please check your internet connection.")
                    } ?: kotlin.run {
                        val data = value?.data
                        val dataStr = Gson().toJson(data)
                        val dataType = object : TypeToken<DoctorVO>(){}.type

                        onSuccess(Gson().fromJson(dataStr,dataType))
                    }
                }
    }

    override fun getPatientByPatientId(
            patientId: String,
            onSuccess: (patient: PatientVO) -> Unit,
            onFailure: (message: String) -> Unit
    ) {
        db.collection(patients).document(patientId)
                .addSnapshotListener { value, error ->
                    error?.let {
                        onFailure(it.message ?: "Please check your internet connection.")
                    } ?: kotlin.run {
                        val data = value?.data
                        val dataStr = Gson().toJson(data)
                        val dataType = object : TypeToken<PatientVO>(){}.type

                        onSuccess(Gson().fromJson(dataStr,dataType))
                    }
                }
    }

    override fun getPatientGeneralAnswers(
        userId: String,
        onSuccess: (answers: List<CaseSummaryVO>) -> Unit,
        onFailure: (message: String) -> Unit
    ) {
        db.collection(patients).document(userId).collection(general_questions_and_answers)
            .addSnapshotListener { value, error ->
                error?.let {
                    onFailure(it.message ?: "Please check your internet connection.")
                } ?: kotlin.run {
                    val answersList : MutableList<CaseSummaryVO> = mutableListOf()
                    val request = value?.documents ?: arrayListOf()

                    for(document in request){
                        val data = document.data
                        val dataStr = Gson().toJson(data)
                        val dataType = object : TypeToken<CaseSummaryVO>(){}.type

                        answersList.add(Gson().fromJson(dataStr,dataType))
                    }
                    onSuccess(answersList)
                }
            }
    }

    override fun getConsultationRequests(
            specialityId: Int,
            onSuccess: (requests : List<ConsultationRequestVO>) -> Unit,
            onFailure: (message: String) -> Unit
    ) {
        db.collection(consultation_requests).whereEqualTo("specialityId",specialityId)
                .addSnapshotListener { value, error ->
                    error?.let {
                        onFailure(it.message ?: "Please check your internet connection.")
                    } ?: kotlin.run {
                        val requestList : MutableList<ConsultationRequestVO> = mutableListOf()
                        val request = value?.documents ?: arrayListOf()
                        for(document in request){
                            val data = document.data
                            val dataStr = Gson().toJson(data)
                            val dataType = object : TypeToken<ConsultationRequestVO>(){}.type

                            requestList.add(Gson().fromJson(dataStr,dataType))
                        }
                        onSuccess(requestList)
                    }
                }
    }

    override fun getRequestedPatientsCaseSummary(id : String, onSuccess: (case: List<CaseSummaryVO>) -> Unit, onFailure: (message: String) -> Unit) {
        db.collection(consultation_requests).document(id).collection(case_summary)
                .addSnapshotListener { value, error ->
                    error?.let {
                        onFailure(it.message ?: "Please check your internet connection.")
                    } ?: kotlin.run {
                        val caseList: MutableList<CaseSummaryVO> = mutableListOf()
                        val request = value?.documents ?: arrayListOf()

                        for (document in request) {
                            val data = document.data
                            val dataStr = Gson().toJson(data)
                            val dataType = object : TypeToken<CaseSummaryVO>(){}.type
                            caseList.add(Gson().fromJson(dataStr,dataType))
                        }
                        onSuccess(caseList)
                    }
                }
    }

    override fun getConsultationById(id: String, onSuccess: (consultation: ConsultationsVO) -> Unit, onFailure: (message: String) -> Unit) {
        db.collection(consultations).document(id)
                .addSnapshotListener { value, error ->
                    error?.let {
                        onFailure(it.message ?: "There is no unfinish consultation.")
                    } ?: kotlin.run {
                        val request = value?.data
                        val dataStr = Gson().toJson(request)
                        val dataType = object : TypeToken<ConsultationsVO>(){}.type
                        onSuccess(Gson().fromJson(dataStr,dataType))
                    }
                }
    }

    override fun getUnfinishedConsultationByDoctroId(userId: String, finished: Boolean, onSuccess: (consultation: List<ConsultationsVO>) -> Unit, onFailure: (message: String) -> Unit) {
        db.collection(consultations).whereEqualTo("doctorId",userId)
                .addSnapshotListener { value, error ->
                    error?.let {
                        onFailure(it.message ?: "There is no unfinish consultation.")
                    } ?: kotlin.run {
                        val request = value?.documents ?: arrayListOf()
                        val consultationList = mutableListOf<ConsultationsVO>()
                        for (document in request){
                            val data = document.data
                            val dataStr = Gson().toJson(data)
                            val dataType = object : TypeToken<ConsultationsVO>(){}.type
                            consultationList.add(Gson().fromJson(dataStr,dataType))
                        }
                        onSuccess(consultationList)
                    }
                }
    }

    override fun getUnfinishedConsultationByPatientId(userId: String, finished : Boolean, onSuccess: (consultation: List<ConsultationsVO>) -> Unit, onFailure: (message: String) -> Unit) {
        db.collection(consultations).whereEqualTo("patientId",userId)
                .addSnapshotListener { value, error ->
                    error?.let {
                        onFailure(it.message ?: "There is no unfinish consultation.")
                    } ?: kotlin.run {
                        val request = value?.documents ?: arrayListOf()
                        val consultationList = mutableListOf<ConsultationsVO>()
                        for (document in request){
                            val data = document.data
                            val dataStr = Gson().toJson(data)
                            val dataType = object : TypeToken<ConsultationsVO>(){}.type
                            consultationList.add(Gson().fromJson(dataStr,dataType))
                        }
                        onSuccess(consultationList)
                    }
                }
    }

    override fun getFinishedConsultationsByDoctorId(
        doctorId : String,
            onSuccess: (consultation: List<ConsultationsVO>) -> Unit,
            onFailure: (message: String) -> Unit
    ) {
        db.collection(consultations).whereEqualTo("doctorId",doctorId)
                .addSnapshotListener { value, error ->
                    error?.let {
                        onFailure(it.message ?: "Please check your internet connection.")
                    } ?: kotlin.run {
                        val consultationsList : MutableList<ConsultationsVO> = mutableListOf()
                        val request = value?.documents ?: arrayListOf()

                        for(document in request){
                            val data = document.data
                            val dataStr = Gson().toJson(data)
                            val dataType = object : TypeToken<ConsultationsVO>(){}.type
                            consultationsList.add(Gson().fromJson(dataStr,dataType))
                        }
                        onSuccess(consultationsList)
                    }
                }
    }

    override fun getFinishedConsultationsByPatientId(
        patientId: String,
        onSuccess: (consultation: List<ConsultationsVO>) -> Unit,
        onFailure: (message: String) -> Unit
    ) {
        db.collection(consultations).whereEqualTo("patientId",patientId)
            .addSnapshotListener { value, error ->
                error?.let {
                    onFailure(it.message ?: "Please check your internet connection!")
                } ?: kotlin.run {
                    val list : MutableList<ConsultationsVO> = mutableListOf()
                    val request = value?.documents ?: arrayListOf()

                    for (document in request){
                        val data = document.data
                        val dataStr = Gson().toJson(data)
                        val dataType = object : TypeToken<ConsultationsVO>(){}.type
                        list.add(Gson().fromJson(dataStr,dataType))
                    }
                    onSuccess(list)
                }
            }
    }

    override fun getConsultationPrescription(
            messageId: String,
            onSuccess: (prescription: List<PrescriptionVO>) -> Unit,
            onFailure: (message: String) -> Unit
    ) {
        db.collection(consultations).document(messageId).collection(prescriptions)
            .addSnapshotListener { value, error ->
                error?.let {
                    onFailure(it.message ?: "Please check your internet connection.")
                } ?: kotlin.run {
                    val prescriptionList : MutableList<PrescriptionVO> = mutableListOf()
                    val request = value?.documents ?: arrayListOf()

                    for (document in request){
                        val data = document.data
                        val dataStr = Gson().toJson(data)
                        val dataType = object : TypeToken<PrescriptionVO>(){}.type

                        prescriptionList.add(Gson().fromJson(dataStr,dataType))
                    }

                    onSuccess(prescriptionList)
                }
            }
    }

    override fun getConsultationCaseSummary(
            messageId: String,
            onSuccess: (case: List<CaseSummaryVO>) -> Unit,
            onFailure: (message: String) -> Unit
    ) {
        db.collection(consultations).document(messageId).collection(case_summary)
            .addSnapshotListener { value, error ->
                error?.let {
                    onFailure(it.message ?: "Please check internet connection.")
                } ?: kotlin.run {
                    val caseList : MutableList<CaseSummaryVO> = mutableListOf()
                    val request = value?.documents ?: arrayListOf()

                    for(document in request){
                        val data = document.data
                        val dataStr = Gson().toJson(data)
                        val dataType = object : TypeToken<CaseSummaryVO>(){}.type

                        caseList.add(Gson().fromJson(dataStr,dataType))
                    }
                    onSuccess(caseList)
                }
            }
    }

    override fun getConsultationMedicalHistory(messageId: String, onSuccess: (history: MedicalHistoryVO) -> Unit, onFailure: (message: String) -> Unit) {
        db.collection(consultations).document(messageId).collection(medical_history).document(messageId)
                .addSnapshotListener { value, error ->
                    error?.let {
                        onFailure(it.message ?: "Please check your internet connection!")
                    } ?: kotlin.run {
                        val request = value?.data
                        if(request != null){
                            val dataStr = Gson().toJson(request)
                            val dataType = object : TypeToken<MedicalHistoryVO>(){}.type
                            onSuccess(Gson().fromJson(dataStr,dataType))
                        }else{
                            onSuccess(MedicalHistoryVO())
                        }
                    }
                }
    }

    override fun getMessage(
            messageId: String,
            onSuccess: (messages: List<LiveChatVO>) -> Unit,
            onFailure: (message: String) -> Unit
    ) {
        db.collection(consultations).document(messageId).collection(conversations).orderBy("timeStamp")
                .addSnapshotListener { value, error ->
                    val chatList : MutableList<LiveChatVO> = mutableListOf()
                    val request = value?.documents ?: arrayListOf()

                    for (documents in request){
                        val data = documents.data
                        val dataStr = Gson().toJson(data)
                        val dataType = object : TypeToken<LiveChatVO>(){}.type

                        chatList.add(Gson().fromJson(dataStr,dataType))
                    }
                    onSuccess(chatList)
                }
    }

    override fun getRecentDoctors(userId: String, onSuccess: (doctors: List<DoctorVO>) -> Unit, onFailure: (message: String) -> Unit) {
        db.collection(patients).document(userId).collection(recent_doctors)
                .addSnapshotListener { value, error ->
                    error?.let {
                        onFailure(it.message ?: "Please check your internet connection.")
                    } ?: kotlin.run {
                        val doctorList : MutableList<DoctorVO> = mutableListOf()
                        val request = value?.documents ?: arrayListOf()

                        for(document in request){
                            val data = document.data
                            val dataStr = Gson().toJson(data)
                            val dataType = object : TypeToken<DoctorVO>() {}.type

                            doctorList.add(Gson().fromJson(dataStr,dataType))
                        }
                        onSuccess(doctorList)
                    }
                }
    }

    override fun getCheckOut(
        userId: String,
        onSuccess: (checkout: CheckOutVO) -> Unit,
        onFailure: (message: String) -> Unit
    ) {
        db.collection(checkOut).document(userId)
            .addSnapshotListener { value, error ->
                error?.let {
                    onFailure(it.message ?: "Check your internet connection.")
                } ?: kotlin.run {
                    val data = value?.data
                    val dataStr = Gson().toJson(data)
                    val dataType = object : TypeToken<CheckOutVO>(){}.type
                    onSuccess(Gson().fromJson(dataStr,dataType))
                }
            }
    }

    override fun getCheckOutPrescription(
        userId: String,
        onSuccess: (prescription: List<PrescriptionVO>) -> Unit,
        onFailure: (message: String) -> Unit
    ) {
        db.collection(checkOut).document(userId).collection(prescriptions)
            .addSnapshotListener { value, error ->
                error?.let {
                    onFailure(it.message ?: "Please check your internet connection.")
                } ?: kotlin.run {
                    val prescriptionList : MutableList<PrescriptionVO> = mutableListOf()
                    val request = value?.documents ?: arrayListOf()

                    for (document in request) {
                        val data = document.data
                        val dataStr = Gson().toJson(data)
                        val dataType = object : TypeToken<PrescriptionVO>(){}.type

                        prescriptionList.add(Gson().fromJson(dataStr,dataType))
                    }
                    onSuccess(prescriptionList)
                }
            }
    }

    override fun sendMessage(messageId: String, message: LiveChatVO) {
        db.collection(consultations).document(messageId).collection(conversations).add(message)
    }

    override fun prescribeMedicine(messageId: String, prescription: PrescriptionVO) {
        db.collection(consultations).document(messageId).collection(prescriptions).document(prescription.medicine).set(prescription)
    }

    override fun addNewDoctor(doctor: DoctorVO) {
        db.collection(doctors).document(doctor.userId).set(doctor)
    }

    override fun addNewPatient(patient: PatientVO) {
        db.collection(patients).document(patient.userId).set(patient)
    }

    override fun addRecentDoctor(userId: String, doctor: DoctorVO) {
        db.collection(patients).document(userId).collection(recent_doctors).document(doctor.userId).set(doctor)
    }

    override fun addConsultation(consultation : ConsultationsVO) {

        db.collection(consultations).document(consultation.id).set(consultation)
    }

    override fun addConsultationCaseSummary(messageId: String, case: CaseSummaryVO) {
        db.collection(consultations).document(messageId).collection(case_summary).document(case.id.toString()).set(case)
    }

    override fun addConsultationPrescription(messageId: String, prescription: PrescriptionVO) {
        db.collection(consultations).document(messageId).collection(prescriptions).document(prescription.medicine).set(prescription)
    }

    override fun addConsultationMedicalHistory(messageId: String, history: MedicalHistoryVO) {
        db.collection(consultations).document(messageId).collection(medical_history).document(messageId).set(history)
    }

    override fun addPatientGeneralAnswers(userId: String, answers: CaseSummaryVO) {
        db.collection(patients).document(userId).collection(general_questions_and_answers).document(answers.id.toString()).set(answers)
    }

    override fun checkOut(userId: String, checkout: CheckOutVO) {
        db.collection(checkOut).document(userId).set(checkout)
    }

    override fun checkOutPrescription(userId: String, prescription: PrescriptionVO) {
        db.collection(checkOut).document(userId).collection(prescriptions).document(prescription.medicine).set(prescription)
    }

    override fun sendConsultationRequest(id : String, consultationRequest : ConsultationRequestVO) {

        db.collection(consultation_requests).document(id).set(consultationRequest)
    }

    override fun sendRequestedPatientCaseSummary(id: String, case: CaseSummaryVO) {
        db.collection(consultation_requests).document(id).collection(case_summary).document(case.id.toString()).set(case)
    }

    override fun uploadImage(
        bitmap: Bitmap,
        onSuccess: (image: String) -> Unit,
        onFailure: (message: String) -> Unit
    ) {
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,baos)
        val data = baos.toByteArray()
        val imageRef = storageReferance.child("images/${UUID.randomUUID()}")
        val uploadTask = imageRef.putBytes(data)

        uploadTask.addOnFailureListener{

        }.addOnCompleteListener{

        }
        val urlTask =uploadTask.continueWithTask{
            return@continueWithTask imageRef.downloadUrl
        }.addOnCompleteListener {task ->
            val imageUrl = task.result
            imageUrl?.let { onSuccess(it.toString()) }
        }
    }

    override fun deleteConsultationRequest(id: String) {
        db.collection(consultation_requests).document(id).delete()
    }

    override fun deleteMedicine(name: String,consultationId : String) {
        db.collection(consultations).document(consultationId).collection(prescriptions).document(name).delete()
    }
}