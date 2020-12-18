package com.nhz.doctorapp.mvp.views

import com.nhz.shared.mvp.views.BaseView

interface LoginView : BaseView {

    fun onTapLogin()
    fun onTapSignUp()
    fun autoLogin()

}