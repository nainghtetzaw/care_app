package com.nhz.doctorapp.mvp.presenters

import com.nhz.doctorapp.mvp.views.OrderMedicineView
import com.nhz.shared.mvp.presenters.BasePresenter

interface OrderMedicinePresenter : BasePresenter<OrderMedicineView> {

    fun onUiReady(consultationId : String )
    fun addCheckOut(address : String)

}