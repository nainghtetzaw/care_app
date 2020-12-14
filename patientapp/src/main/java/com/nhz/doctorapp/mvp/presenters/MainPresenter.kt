package com.nhz.doctorapp.mvp.presenters

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.nhz.doctorapp.mvp.views.MainView
import com.nhz.shared.mvp.presenters.BasePresenter

interface MainPresenter : BasePresenter<MainView> {

    fun onUiReady(context: Context,lifecycleOwner: LifecycleOwner)

}