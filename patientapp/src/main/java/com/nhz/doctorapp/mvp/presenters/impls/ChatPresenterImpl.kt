package com.nhz.doctorapp.mvp.presenters.impls

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.nhz.doctorapp.mvp.presenters.ChatPresenter
import com.nhz.doctorapp.mvp.views.ChatView
import com.nhz.shared.data.vos.DoctorVO
import com.nhz.shared.data.vos.LiveChatVO
import com.nhz.shared.data.vos.PatientVO
import com.nhz.shared.mvp.presenters.AbstractBasePresenter

class ChatPresenterImpl : AbstractBasePresenter<ChatView>(),ChatPresenter {

    private lateinit var doctor : DoctorVO
    private lateinit var patient : PatientVO
    private var medicineList : MutableList<String> = mutableListOf()

    override fun onUiReady(consultationId: String, context: Context, lifecycleOwner: LifecycleOwner) {
        getConsultationCaseSummaryAndPatientGeneralInfo(consultationId)
        getConsultationInfo(consultationId)
        getMessageList(consultationId)
        getConsultationPrescription(consultationId)
    }

    override fun sendMessage(consultationId: String,message : String,timeStamp : String) {
        mModel.sendMessage(
                consultationId,
                LiveChatVO(
                        message = message,
                        sender_id = patient.userId,
                        sender_name = patient.username,
                        sender_image = patient.image,
                        receiver_id = doctor.userId,
                        receiver_name = doctor.name,
                        receiver_image = doctor.profileImage,
                        timeStamp = timeStamp,
                        medicineList = medicineList
                ))
    }

    private fun getMessageList(consultationId: String){
        mModel.getPatientByPatientIdAndSaveToDatabase("72JXNg3bVUZ0FRyanMNiNm2WLPn1",{patientVO ->
            patient = patientVO
            mModel.getMessageFromNetwork(consultationId,{liveChat ->
                mView?.showMessageList(liveChat,patientVO.userId)
            },{})
        },{})
    }

    private fun getConsultationCaseSummaryAndPatientGeneralInfo(consultationId : String){
        mModel.getConsultationCaseSummaryAndSaveToDatabase(consultationId,{
            mView?.showSpecialityQuestionAndAnswerData(it)
        },{})
        mModel.getPatientGeneralAnswersAndSaveToDatabase("72JXNg3bVUZ0FRyanMNiNm2WLPn1",{
            mView?.showGeneralQuestionAndAnswerData(it)
        },{})
    }

    private fun getConsultationInfo(consultationId: String){
        mModel.getUnfinishedConsultationFromNetwork("72JXNg3bVUZ0FRyanMNiNm2WLPn1",false,{
            val accept = it.filter { consultation -> consultation.id == consultationId }
            doctor = accept[0].doctor_info!!
        },{})
    }

    private fun getConsultationPrescription(consultationId : String){
        mModel.getConsultationPrescriptionAndSaveToDatabase(consultationId,{ prescriptions ->
            prescriptions.forEach {
                medicineList.add(it.medicine)
            }
        },{})
    }
}