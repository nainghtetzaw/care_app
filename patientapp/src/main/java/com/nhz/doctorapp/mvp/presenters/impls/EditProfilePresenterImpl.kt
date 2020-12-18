package com.nhz.doctorapp.mvp.presenters.impls

import android.graphics.Bitmap
import com.nhz.doctorapp.mvp.presenters.EditProfilePresenter
import com.nhz.doctorapp.mvp.views.EditProfileView
import com.nhz.shared.data.vos.PatientVO
import com.nhz.shared.mvp.presenters.AbstractBasePresenter

class EditProfilePresenterImpl : AbstractBasePresenter<EditProfileView>(),EditProfilePresenter {

    override fun onTapSaveProfile(patient: PatientVO,bitmap : Bitmap) {
        mModel.uploadImage(bitmap,{
            mModel.addNewPatient(PatientVO(
                mAuthModel.getUserToken(),
                patient.username,
                patient.phoneNumber,
                mAuthModel.getEmail(),
                patient.date_of_birth,
                mAuthModel.getFbToken(),
                it
            ))
        },{})

    }
}