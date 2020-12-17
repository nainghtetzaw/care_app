package com.nhz.doctorapp.mvp.presenters.interfaces

import com.nhz.doctorapp.mvp.views.PrescribeView
import com.nhz.shared.data.vos.PrescriptionVO
import com.nhz.shared.mvp.presenters.BasePresenter

interface PrescribePresenter : BasePresenter<PrescribeView> {

    fun onClickAdd(consultationId : String,prescription : PrescriptionVO)

}