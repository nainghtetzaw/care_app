package com.nhz.doctorapp.mvp.presenters

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.nhz.doctorapp.mvp.views.ChatView
import com.nhz.shared.mvp.presenters.BasePresenter

interface ChatPresenter : BasePresenter<ChatView> {

    fun onUiReady(consultationId : String, context : Context, lifecycleOwner: LifecycleOwner)
    fun sendMessage(consultationId: String, message : String,timeStamp : String)

}