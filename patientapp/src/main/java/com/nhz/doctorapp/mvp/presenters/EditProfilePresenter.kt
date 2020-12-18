package com.nhz.doctorapp.mvp.presenters

import android.graphics.Bitmap
import com.nhz.doctorapp.mvp.views.EditProfileView
import com.nhz.shared.data.vos.PatientVO
import com.nhz.shared.mvp.presenters.BasePresenter

interface EditProfilePresenter : BasePresenter<EditProfileView> {
    
    fun onTapSaveProfile(patient : PatientVO,bitmap: Bitmap)

}