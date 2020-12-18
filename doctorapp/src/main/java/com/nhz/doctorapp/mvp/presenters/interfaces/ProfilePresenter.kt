package com.nhz.doctorapp.mvp.presenters.interfaces

import com.nhz.doctorapp.mvp.views.ProfileView
import com.nhz.shared.mvp.presenters.BasePresenter

interface ProfilePresenter : BasePresenter<ProfileView> {

    fun onUiReady()
    fun logout()

}