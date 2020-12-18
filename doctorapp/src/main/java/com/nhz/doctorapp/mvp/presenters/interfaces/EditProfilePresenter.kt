package com.nhz.doctorapp.mvp.presenters.interfaces

import android.graphics.Bitmap
import com.nhz.doctorapp.mvp.views.EditProfileView
import com.nhz.shared.data.vos.DoctorVO
import com.nhz.shared.mvp.presenters.BasePresenter

interface EditProfilePresenter : BasePresenter<EditProfileView> {

    fun onUiReady()
    fun onTapSaveProfile(doctor : DoctorVO,bitmap: Bitmap)

}