package com.nhz.doctorapp.mvp.presenters.impls

import android.content.Context
import android.widget.Toast
import com.nhz.doctorapp.mvp.presenters.SignUpPresenter
import com.nhz.doctorapp.mvp.views.SignUpView
import com.nhz.shared.data.vos.PatientVO
import com.nhz.shared.mvp.presenters.AbstractBasePresenter

class SignUpPresenterImpl : AbstractBasePresenter<SignUpView>() , SignUpPresenter {
    override fun signUp(email: String, username: String, password: String,context : Context) {
        mAuthModel.signUp(username,password,email,{
            if (it){
                mModel.addNewPatient(PatientVO(mAuthModel.getUserToken(),username,email = email,deviceId = mAuthModel.getFbToken()))
                mView?.navigateToMainActivity()
            }
        },{
            Toast.makeText(context,it,Toast.LENGTH_SHORT).show()
        })
    }
}