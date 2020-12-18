package com.nhz.doctorapp.mvp.presenters

import android.content.Context
import com.nhz.doctorapp.mvp.views.LoginView
import com.nhz.shared.mvp.presenters.BasePresenter

interface LoginPresenter : BasePresenter<LoginView> {

    fun login(email : String,password : String,context: Context)
    fun navigateToSignUpPage()
    fun isAlreadyLoginOrNot()

}