package com.nhz.doctorapp.mvp.presenters.impls

import android.content.Context
import android.widget.Toast
import com.nhz.doctorapp.mvp.presenters.interfaces.LoginPresenter
import com.nhz.doctorapp.mvp.views.LoginView
import com.nhz.shared.mvp.presenters.AbstractBasePresenter

class LoginPresenterImpl : AbstractBasePresenter<LoginView>(),LoginPresenter{
    override fun login(email: String, password: String,context: Context) {
        mAuthModel.login(email,password,{
            if (it){
                mView?.onTapLogin()
            }
        },{
            Toast.makeText(context,it, Toast.LENGTH_SHORT).show()
        })
    }

    override fun navigateToSignUpPage() {
        mView?.onTapSignUp()
    }

    override fun isAlreadyLoginOrNot() {
        if (mAuthModel.getUserToken().isNullOrEmpty()){
            val userId = mAuthModel.getUserToken()
            mView?.autoLogin()
        }
    }
}