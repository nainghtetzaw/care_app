package com.nhz.doctorapp.mvp.presenters.interfaces

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.nhz.doctorapp.delegates.HomeDelegate
import com.nhz.doctorapp.mvp.views.HomeView
import com.nhz.shared.mvp.presenters.BasePresenter

interface HomePresenter : BasePresenter<HomeView> , HomeDelegate {

    fun onUiReady(context: Context,lifecycleOwner: LifecycleOwner)

}