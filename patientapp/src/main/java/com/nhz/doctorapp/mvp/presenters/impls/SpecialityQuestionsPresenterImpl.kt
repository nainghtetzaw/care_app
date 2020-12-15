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

    private lateinit var consultationRequestId : String

    override fun onUiReady(id : Int,oldOrNew : Boolean,context: Context, lifecycleOwner: LifecycleOwner) {
        consultationRequestId = UUID.randomUUID().toString()
        getSpecialityQuestions(id,context, lifecycleOwner)
    }

    override fun showConfirmDialogAndSendAnswersToNetwork(data : List<CaseSummaryVO>) {

        data.forEach {
            mModel.sendRequestedPatientCaseSummary("72JXNg3bVUZ0FRyanMNiNm2WLPn1",it)
        }
        mView?.onStartConsultation(consultationRequestId)
    }

    override fun createConsultationRequest(id: Int, oldOrNew: Boolean,lifecycleOwner: LifecycleOwner) {
        makeConsultationRequest(id,oldOrNew,lifecycleOwner)
    }

    private fun makeConsultationRequest(specialityId: Int,oldOrNew: Boolean,lifecycleOwner: LifecycleOwner){
        mModel.getPatientInfoFromDatabase().observe(lifecycleOwner, Observer {patient ->
            mModel.sendConsultationRequestPatient(
                "72JXNg3bVUZ0FRyanMNiNm2WLPn1",
                ConsultationRequestVO(consultationRequestId,patient ,specialityId,true,oldOrNew))
        })
    }

    private fun getSpecialityQuestions(id : Int,context: Context,lifecycleOwner: LifecycleOwner){
        mModel.getAllSpecialityQuestionsByIdAndSaveToDatabase(id,{
            mView?.showSpecialityQuestions(it)
        },{})
    }

}