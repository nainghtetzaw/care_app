package com.nhz.doctorapp.mvp.presenters.impls

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.nhz.doctorapp.mvp.presenters.interfaces.ChatPresenter
import com.nhz.doctorapp.mvp.views.ChatView
import com.nhz.shared.data.vos.DoctorVO
import com.nhz.shared.data.vos.LiveChatVO
import com.nhz.shared.mvp.presenters.AbstractBasePresenter

class ChatPresenterImpl : AbstractBasePresenter<ChatView>(),ChatPresenter {

    private lateinit var doctor : DoctorVO
    private var mMedicineList : MutableList<String> = mutableListOf()

    override fun onUiReady(patientId : String,consultationId: String,context: Context, lifecycleOwner: LifecycleOwner) {
        getConsultationCaseSummaryAndPatientGeneralInfo(patientId,consultationId)
        getConsultationPrescription(consultationId)
        getMessageList(consultationId)
    }

    override fun sendMessage(
            consultationId: String,
            message: String,
            timeStamp : String,
            patientName : String,
            patientId : String,
            patientImage : String
    ) {
        mModel.sendMessage(consultationId, LiveChatVO(
                message = message,
                sender_id = doctor.userId,
                sender_name = doctor.name,
                sender_image = doctor.profileImage,
                receiver_id = patientId,
                receiver_name = patientName,
                receiver_image = patientImage,
                timeStamp = timeStamp,
                medicineList = mMedicineList))
    }

    private fun getMessageList(consultationId: String){
        mModel.getMessageFromNetwork(consultationId,{
            mView?.showMessageList(it,doctor.userId)
        },{})
    }

    private fun getConsultationCaseSummaryAndPatientGeneralInfo(patientId : String,consultationId : String){
        mModel.getConsultationCaseSummaryAndSaveToDatabase(consultationId,{
            mView?.showSpecialityQuestionAndAnswerData(it)
        },{})
        mModel.getPatientGeneralAnswersAndSaveToDatabase(patientId,{
            mView?.showGeneralQuestionAndAnswerData(it)
        },{})
    }

    private fun getConsultationPrescription(consultationId: String){
        mModel.getDoctorByDoctorIdAndSaveToDatabase("1234512345",{ doctorVO ->
            doctor = doctorVO
            mModel.getConsultationPrescriptionAndSaveToDatabase(consultationId,{ prescriptions ->
                if (prescriptions.count() != 0){
                    prescriptions.forEach {
                        mMedicineList.add(it.medicine)
                    }
                }
            },{})
        },{})
    }
}