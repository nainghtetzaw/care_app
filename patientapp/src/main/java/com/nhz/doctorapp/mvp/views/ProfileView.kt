package com.nhz.doctorapp.mvp.views

import com.nhz.shared.data.vos.PatientVO
import com.nhz.shared.mvp.views.BaseView

interface ProfileView : BaseView {

    fun showProfileInfo(data : PatientVO)
    fun showEmptyViewPod()

}