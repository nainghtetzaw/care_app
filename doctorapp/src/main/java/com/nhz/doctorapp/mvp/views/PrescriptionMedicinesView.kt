package com.nhz.doctorapp.mvp.views

import com.nhz.shared.data.vos.MedicinesVO
import com.nhz.shared.mvp.views.BaseView

interface PrescriptionMedicinesView : BaseView {

    fun showMedicineList(data : List<MedicinesVO>)
    fun showPrescribeMedicineFragment(name : String,consultationId : String,price : Int)

}