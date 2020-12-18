package com.nhz.doctorapp.mvp.views

import com.nhz.shared.data.vos.PrescriptionVO
import com.nhz.shared.mvp.views.BaseView

interface PrescriptionHistoryView  : BaseView{

    fun showPrescriptionData(data : List<PrescriptionVO>)

}