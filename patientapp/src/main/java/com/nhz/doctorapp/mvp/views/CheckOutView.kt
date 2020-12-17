package com.nhz.doctorapp.mvp.views

import com.nhz.shared.data.vos.PrescriptionVO
import com.nhz.shared.mvp.views.BaseView

interface CheckOutView : BaseView {

    fun showPrescriptionData(data : List<PrescriptionVO>)
    fun showAddressAndPrice(address : String,total : String,subtotal : String)

}