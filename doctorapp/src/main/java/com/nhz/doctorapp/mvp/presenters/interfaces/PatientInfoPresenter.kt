package com.nhz.doctorapp.mvp.presenters.interfaces

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.nhz.doctorapp.mvp.views.PatientInfoView
import com.nhz.shared.mvp.presenters.BasePresenter

interface PatientInfoPresenter : BasePresenter<PatientInfoView> {

    fun onUiReady(patientId : String,consultationId : String,context : Context,lifecycleOwner: LifecycleOwner)
    fun navigateToChatActivity()

}