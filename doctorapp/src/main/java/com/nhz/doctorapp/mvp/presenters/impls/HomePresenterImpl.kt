package com.nhz.doctorapp.mvp.presenters.impls

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.nhz.doctorapp.mvp.presenters.interfaces.HomePresenter
import com.nhz.doctorapp.mvp.views.HomeView
import com.nhz.shared.data.vos.ConsultationRequestVO
import com.nhz.shared.data.vos.ConsultationsVO
import com.nhz.shared.data.vos.DoctorVO
import com.nhz.shared.data.vos.PatientVO
import com.nhz.shared.mvp.presenters.AbstractBasePresenter

class HomePresenterImpl : AbstractBasePresenter<HomeView>(),HomePresenter {

    private lateinit var doctorInfo : DoctorVO
    private var requestList :  MutableList<ConsultationRequestVO> = mutableListOf()
    private var historyList : MutableList<ConsultationsVO> = mutableListOf()

    override fun onUiReady(context: Context, lifecycleOwner: LifecycleOwner) {
        getConsultationRequestsBySpecialityId()
        getConsultationHistory()
        emptyOrNot()
    }

    override fun onTapAcceptRequest(requestVO: ConsultationRequestVO) {
        mModel.addConsultation(ConsultationsVO(
            requestVO.id,
            doctorInfo.userId,
            requestVO.patient?.
            userId!!,
            doctorInfo,
            requestVO.patient,
            finished = false,
            accept = true))
        mModel.sendConsultationRequestPatient(requestVO.patient?.userId!!, ConsultationRequestVO(
            requestVO.id,
            requestVO.patient,
            requestVO.specialityId,
            false))
        mView?.navigateToPatientInfoActivity(requestVO.patient!!,requestVO.id)
    }

    override fun onTapRemoveRequest(id: String) {
        requestList.removeIf { it.doctorId == id }
        mView?.showConsultationRequestData(requestList,doctorInfo.userId)
    }

    override fun onTapSelectTime() {
        mView?.showSetConsultationTimeFragmentDialog()
    }

    override fun onTapMedication(consultationId : String,patientId : String) {
        mView?.showCaseSummaryHistoryDialogFragment(consultationId,patientId)
    }

    override fun onTapPrescription(consultationId: String) {
        mView?.showPrescriptionHistoryDialogFragment(consultationId)
    }

    override fun onTapNote(consultationId: String,patientName : String,patientBd : String) {
        mView?.showMedicalHistoryDialogFragment(consultationId,patientName,patientBd)
    }

    private fun getConsultationRequestsBySpecialityId(){
        mModel.getDoctorByDoctorIdAndSaveToDatabase(mAuthModel.getUserToken(),{doctor ->
            doctorInfo = doctor
            mView?.showDoctorInfo(doctor)
            mModel.getConsultationRequestedPatientAndSaveToDatabase(doctor.specialityId,{
                val request = it.filter { request -> request.available && request.doctorId != doctor.userId}
                requestList = request.toMutableList()
                mView?.showConsultationRequestList()
                mView?.hideEmpty()
                mView?.showConsultationRequestData(request,doctor.userId)
            },{
                mView?.hideConsultationRequestList()
            })
        },{})
    }

    private fun getConsultationHistory(){
        mModel.getFinishedConsultationsByDoctorIdAndSaveToDatabase("1234512345",{
            val finished = it.filter { consultation -> consultation.finished }
            historyList = finished.toMutableList()
            if (finished.count() != 0){
                mView?.showConsultationHistoryList()
                mView?.hideEmpty()
                mView?.showConsultationHistoryData(finished)
            }else {
                mView?.hideConsultationHistoryList()
                mView?.hideEmpty()
            }
        },{
            mView?.hideConsultationHistoryList()
        })
    }

    private fun emptyOrNot(){
        if (requestList.count() == 0 && historyList.count() == 0){
            mView?.showEmpty()
            mView?.hideConsultationHistoryList()
            mView?.hideConsultationRequestList()
        }else{
            mView?.hideEmpty()
            mView?.showConsultationHistoryList()
            mView?.showConsultationRequestList()
        }
    }
}