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

    override fun onUiReady(context: Context, lifecycleOwner: LifecycleOwner) {
        getConsultationRequestsBySpecialityId()
        getConsultationHistory()
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
            false,requestVO.
            old_or_new))
        mView?.navigateToPatientInfoActivity(requestVO.patient!!,requestVO.id)
    }

    private fun getConsultationRequestsBySpecialityId(){
        mModel.getDoctorByDoctorIdAndSaveToDatabase("1234512345",{doctor ->
            doctorInfo = doctor
            mView?.showDoctorInfo(doctor)
            mModel.getConsultationRequestedPatientAndSaveToDatabase(doctor.specialityId,{
                val request = it.filter { request -> request.available || request.doctorId == doctor.userId }
                mView?.showConsultationRequestList()
                mView?.showConsultationRequestData(request)
            },{
                mView?.hideConsultationRequestList()
            })
        },{})
    }

    private fun getConsultationHistory(){
        mModel.getFinishedConsultationsByDoctorIdAndSaveToDatabase("1234512345",{
            val finished = it.filter { consultation -> consultation.finished }
            if (finished.count() != 0){
                mView?.showConsultationHistoryList()
                mView?.showConsultationHistoryData(finished)
            }else {
                mView?.hideConsultationHistoryList()
            }
        },{
            mView?.hideConsultationHistoryList()
        })
    }
}