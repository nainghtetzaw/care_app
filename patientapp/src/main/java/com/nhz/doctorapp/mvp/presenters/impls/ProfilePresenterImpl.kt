package com.nhz.doctorapp.mvp.presenters.impls

import com.nhz.doctorapp.mvp.presenters.ProfilePresenter
import com.nhz.doctorapp.mvp.views.ProfileView
import com.nhz.shared.mvp.presenters.AbstractBasePresenter

class ProfilePresenterImpl : AbstractBasePresenter<ProfileView>(),ProfilePresenter {
    override fun onUiReady() {
        mModel.getPatientByPatientIdAndSaveToDatabase(mAuthModel.getUserToken(),{
            if(it.date_of_birth != ""){
                mView?.showProfileInfo(it)
            }else{
                mView?.showEmptyViewPod()
            }

        },{})
    }

    override fun logout() {
        mAuthModel.logout()
    }
}