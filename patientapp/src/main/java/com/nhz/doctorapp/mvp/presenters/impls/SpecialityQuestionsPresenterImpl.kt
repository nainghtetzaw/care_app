package com.nhz.doctorapp.mvp.presenters.impls

import android.content.Context
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.nhz.doctorapp.mvp.presenters.SpecialityQuestionsPresenter
import com.nhz.doctorapp.mvp.views.SpecialityQuestionView
import com.nhz.shared.data.vos.CaseSummaryVO
import com.nhz.shared.data.vos.ConsultationRequestVO
import com.nhz.shared.data.vos.PatientVO
import com.nhz.shared.data.vos.SpecialityQuestionsVO
import com.nhz.shared.mvp.presenters.AbstractBasePresenter
import java.util.*
import kotlin.collections.HashMap

class SpecialityQuestionsPresenterImpl : AbstractBasePresenter<SpecialityQuestionView>(),SpecialityQuestionsPresenter {

    private lateinit var mConsultationRequestId : String

    override fun onUiReady(id : Int,consultationId: String,context: Context, lifecycleOwner: LifecycleOwner) {
        mConsultationRequestId = consultationId
        getSpecialityQuestions(id,context, lifecycleOwner)
    }

    override fun showConfirmDialogAndSendAnswersToNetwork(data : List<CaseSummaryVO>) {

        data.forEach {
            mModel.sendRequestedPatientCaseSummary(mAuthModel.getUserToken(),it)
        }
        mView?.onStartConsultation(mConsultationRequestId)
    }

    override fun createConsultationRequest(id: Int,consultationId : String,doctorId : String,lifecycleOwner: LifecycleOwner) {
        makeConsultationRequest(id,consultationId,doctorId,lifecycleOwner)
    }

    private fun makeConsultationRequest(specialityId: Int,consultationId : String,doctorId: String,lifecycleOwner: LifecycleOwner){
        mModel.getPatientByPatientIdAndSaveToDatabase(mAuthModel.getUserToken(),{patient ->
            mModel.sendConsultationRequestPatient(
                    mAuthModel.getUserToken(),
                    ConsultationRequestVO(consultationId,patient ,specialityId,true,doctorId = doctorId))
        },{})

    }

    private fun getSpecialityQuestions(id : Int,context: Context,lifecycleOwner: LifecycleOwner){
        mModel.getAllSpecialityQuestionsByIdAndSaveToDatabase(id,{
            mView?.showSpecialityQuestions(it)
        },{})
    }

}