package com.nhz.doctorapp.mvp.presenters.interfaces

import android.content.Context
import com.nhz.doctorapp.mvp.views.SignUpView
import com.nhz.shared.mvp.presenters.BasePresenter

interface SignUpPresenter : BasePresenter<SignUpView> {

    fun signUp(email : String,username : String,password : String,context: Context)

}