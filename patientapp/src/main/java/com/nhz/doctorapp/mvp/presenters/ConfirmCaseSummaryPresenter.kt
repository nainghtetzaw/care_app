package com.nhz.doctorapp.mvp.presenters

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.nhz.doctorapp.mvp.views.ConfirmCaseSummaryView
import com.nhz.shared.mvp.presenters.BasePresenter

interface ConfirmCaseSummaryPresenter : BasePresenter<ConfirmCaseSummaryView> {

    fun onUiReady(id : String,context: Context,lifecycleOwner: LifecycleOwner)
    fun navigateToMainActivity(id : String,lifecycleOwner: LifecycleOwner)

}