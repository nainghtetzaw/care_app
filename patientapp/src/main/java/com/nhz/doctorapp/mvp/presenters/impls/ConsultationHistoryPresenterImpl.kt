package com.nhz.doctorapp.mvp.presenters.impls

import android.content.Context
import android.widget.Toast
import com.nhz.doctorapp.mvp.presenters.ConsultationHistoryPresenter
import com.nhz.doctorapp.mvp.views.ConsultationHistoryView
import com.nhz.shared.mvp.presenters.AbstractBasePresenter

class ConsultationHistoryPresenterImpl : AbstractBasePresenter<ConsultationHistoryView>(), ConsultationHistoryPresenter {
    override fun onUiReady(context: Context) {
        getConsultationHistory(context)
    }

    override fun onTapNote(consultationId: String,patientName : String,patientBd : String) {
        mView?.showMedicalHistoryDialogFragment(consultationId,patientName,patientBd)
    }

    override fun onTapPrescription(consultationId: String) {
        mView?.showPrescriptionDialogFragment(consultationId)
    }

    private fun getConsultationHistory(context : Context){
        mModel.getUnfinishedConsultationFromNetworkByPatientId(mAuthModel.getUserToken(),false,{
            val finished = it.filter { it.finished }
            mView?.showConsultationHistoryData(finished)
        },{})
//        mModel.getFinishedConsultationsByPatientIdAndSaveToDatabase("72JXNg3bVUZ0FRyanMNiNm2WLPn1",{
//            val consultation = it.filter { it.finished }
//            mView?.showConsultationHistoryData(consultation)
//        },{})
    }
}