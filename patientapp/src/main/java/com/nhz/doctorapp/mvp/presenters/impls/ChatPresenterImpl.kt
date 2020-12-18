package com.nhz.doctorapp.mvp.presenters.impls

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.nhz.doctorapp.getCurrentDate
import com.nhz.doctorapp.getCurrentTime
import com.nhz.doctorapp.getTimeStamp
import com.nhz.doctorapp.mvp.presenters.ChatPresenter
import com.nhz.doctorapp.mvp.views.ChatView
import com.nhz.shared.data.vos.DoctorVO
import com.nhz.shared.data.vos.LiveChatVO
import com.nhz.shared.data.vos.PatientVO
import com.nhz.shared.mvp.presenters.AbstractBasePresenter

class ChatPresenterImpl : AbstractBasePresenter<ChatView>(),ChatPresenter {

    private lateinit var patient : PatientVO
    private lateinit var doctor : DoctorVO
    private var medicineList : MutableList<String> = mutableListOf()
    private var mConsultationId : String = ""

    override fun onUiReady(consultationId: String, context: Context, lifecycleOwner: LifecycleOwner) {
        mConsultationId = consultationId
        getConsultationInfo(consultationId)
        getConsultationCaseSummaryAndPatientGeneralInfo(consultationId)
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
                        timeStamp = getCurrentDate(),
                        time = getCurrentTime(),
                        medicineList = medicineList
                ))
    }

    override fun onTapOrderMedicine() {
        mView?.navigateToOrderMedicineActivity(mConsultationId)
    }


    private fun getMessageList(consultationId: String){
        mModel.getPatientByPatientIdAndSaveToDatabase(mAuthModel.getUserToken(),{patientVO ->
            patient = patientVO
            mView?.setDoctorAndPatientInfo(patientVO.username,patientVO.date_of_birth)
            mModel.getMessageFromNetwork(consultationId,{liveChat ->
                mView?.showMessageList(liveChat,patientVO.userId)
            },{})
        },{})
    }

    private fun getConsultationCaseSummaryAndPatientGeneralInfo(consultationId : String){
        mModel.getConsultationCaseSummaryAndSaveToDatabase(consultationId,{
            mView?.showSpecialityQuestionAndAnswerData(it)
        },{})
        mModel.getPatientGeneralAnswersAndSaveToDatabase(mAuthModel.getUserToken(),{
            mView?.showGeneralQuestionAndAnswerData(it)
        },{})
    }

    private fun getConsultationInfo(consultationId: String){
        mModel.getUnfinishedConsultationFromNetworkByPatientId(mAuthModel.getUserToken(),false,{ list ->
            list.filter { consultation -> consultation.id == consultationId }.let { doctor = it[0].doctor_info!! }
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