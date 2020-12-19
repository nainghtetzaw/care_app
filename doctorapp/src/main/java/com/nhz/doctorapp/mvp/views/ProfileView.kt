package com.nhz.doctorapp.mvp.views

import com.nhz.shared.data.vos.DoctorVO
import com.nhz.shared.mvp.views.BaseView

interface ProfileView : BaseView {

    fun showDoctorInfo(doctor: DoctorVO)
    fun navigateToLoginActivity()

}