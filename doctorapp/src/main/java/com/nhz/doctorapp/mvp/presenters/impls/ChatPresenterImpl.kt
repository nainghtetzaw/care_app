package com.nhz.doctorapp.mvp.presenters.impls

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.nhz.doctorapp.getCurrentDate
import com.nhz.doctorapp.getCurrentTime
import com.nhz.doctorapp.getTimeStamp
import com.nhz.doctorapp.mvp.presenters.interfaces.ChatPresenter
import com.nhz.doctorapp.mvp.views.ChatView
import com.nhz.shared.data.vos.DoctorVO
import com.nhz.shared.data.vos.LiveChatVO
import com.nhz.shared.mvp.presenters.AbstractBasePresenter
import java.util.*

class ChatPresenterImpl : AbstractBasePresenter<ChatView>(),ChatPresenter {

    private lateinit var doctor : DoctorVO
    private var conId : String = ""
    private var mMedicineList : MutableList<String> = mutableListOf()

    override fun onUiReady(patientId : String,consultationId: String,context: Context, lifecycleOwner: LifecycleOwner) {
        conId = consultationId
        getConsultationCaseSummaryAndPatientGeneralInfo(patientId,consultationId)
        getConsultationPrescription(consultationId)
        getMessageList(consultationId)
        getConsultation(consultationId)
    }

    override fun sendMessage(
            consultationId: String,
            message: String,
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
                timeStamp = getCurrentDate(),
                time = getCurrentTime(),
                medicineList = mMedicineList))
    }

    override fun navigateToMedicineList() {
        mView?.startPrescription(doctor.specialityId,conId)
    }

    override fun navigateToSpecialityQuestionsActivity() {
        mView?.openQuestions(doctor.specialityId,conId)
    }

    override fun navigateToMedicalHistoryActivity(patientName: String,patientBd : String) {
        mView?.openMedicalHistory(conId,patientName, patientBd)
    }

    private fun getConsultation(consultationId: String) {
        mModel.getConsultationByIdFromNetwork(consultationId,{
            if (it.finished){
                mView?.disableMessaging()
            }
        },{})
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
        mModel.getDoctorByDoctorIdAndSaveToDatabase(mAuthModel.getUserToken(),{ doctorVO ->
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