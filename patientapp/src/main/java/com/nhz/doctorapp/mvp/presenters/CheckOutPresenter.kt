package com.nhz.doctorapp.mvp.presenters

import com.nhz.doctorapp.mvp.views.CheckOutView
import com.nhz.shared.mvp.presenters.BasePresenter

interface CheckOutPresenter : BasePresenter<CheckOutView> {

    fun onUiReady(consultationId : String)
//    fun finishCheckOut()

}