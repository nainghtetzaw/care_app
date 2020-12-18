package com.nhz.doctorapp.mvp.presenters.impls

import android.graphics.Bitmap
import com.nhz.doctorapp.mvp.presenters.interfaces.EditProfilePresenter
import com.nhz.doctorapp.mvp.views.EditProfileView
import com.nhz.shared.data.vos.DoctorVO
import com.nhz.shared.mvp.presenters.AbstractBasePresenter

class EditProfilePresenterImpl : AbstractBasePresenter<EditProfileView>(),EditProfilePresenter {
    override fun onUiReady() {
        mModel.getAllSpecialitiesAndSaveToDatabase({
            mView?.showSpecialities(it)
        },{})
    }

    override fun onTapSaveProfile(doctor: DoctorVO,bitmap: Bitmap) {
        mModel.uploadImage(bitmap,{
            mModel.addNewDoctor(
                DoctorVO(
                    mAuthModel.getUserToken(),
                    mAuthModel.getUsername(),
                    mAuthModel.getEmail(),
                    doctor.name,
                    doctor.phone,
                    doctor.experience,
                    doctor.description,
                    it,
                    doctor.degree,
                    doctor.gender,
                    doctor.date_of_birth,
                    doctor.specialityId,
                    doctor.specialityType,
                    mAuthModel.getFbToken()
                ))
        },{})
    }
}