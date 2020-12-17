package com.nhz.doctorapp.mvp.views

import com.nhz.shared.data.vos.PrescriptionVO
import com.nhz.shared.mvp.views.BaseView

interface OrderMedicineView : BaseView {

    fun showMedicineList(data : List<PrescriptionVO>)
    fun showTotalPrice(price: Int)

}