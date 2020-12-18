package com.nhz.doctorapp.mvp.presenters

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.nhz.doctorapp.delegates.HomeDelegates
import com.nhz.doctorapp.delegates.RecentDoctorDelegate
import com.nhz.doctorapp.mvp.views.HomeView
import com.nhz.shared.data.vos.CaseSummaryVO
import com.nhz.shared.mvp.presenters.BasePresenter

interface HomePresenter : BasePresenter<HomeView>,HomeDelegates,RecentDoctorDelegate {

    fun onUiReady(context : Context,lifecycleOwner: LifecycleOwner)
    fun getAcceptedConsultation(finished : Boolean)
    fun navigateToChatActivity()

}