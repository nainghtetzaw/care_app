package com.nhz.doctorapp.mvp.views

import com.nhz.shared.data.vos.PrescriptionVO
import com.nhz.shared.mvp.views.BaseView

interface ConsultationPrescriptionView : BaseView {

    fun showPrescriptionList(data : List<PrescriptionVO>)

}