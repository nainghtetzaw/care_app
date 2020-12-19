package com.nhz.doctorapp.mvp.presenters.impls

import com.nhz.doctorapp.mvp.presenters.interfaces.ProfilePresenter
import com.nhz.doctorapp.mvp.views.ProfileView
import com.nhz.shared.mvp.presenters.AbstractBasePresenter

class ProfilePresenterImpl : AbstractBasePresenter<ProfileView>(),ProfilePresenter {
    override fun onUiReady() {
        mModel.getDoctorByDoctorIdAndSaveToDatabase(mAuthModel.getUserToken(),{
            mView?.showDoctorInfo(it)
        },{})
    }

    override fun logout() {
        mAuthModel.logout()
        mView?.navigateToLoginActivity()
    }
}