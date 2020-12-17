package com.nhz.doctorapp.mvp.presenters.interfaces

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.nhz.doctorapp.delegates.MedicineListDelegate
import com.nhz.doctorapp.mvp.views.PrescriptionMedicinesView
import com.nhz.shared.data.vos.MedicinesVO
import com.nhz.shared.mvp.presenters.BasePresenter

interface PrescriptionMedicinesPresenter : BasePresenter<PrescriptionMedicinesView>,MedicineListDelegate {

    fun onUiReady(specialityId : Int,consultationId : String,context: Context,lifecycleOwner: LifecycleOwner)
    fun addOldDataToMedicineList()
    fun getOldData() : List<MedicinesVO>
    fun sendMessagePrescription()
    fun finishConsultation()

}