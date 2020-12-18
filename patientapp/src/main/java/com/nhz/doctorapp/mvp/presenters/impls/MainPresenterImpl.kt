package com.nhz.doctorapp.mvp.presenters.impls

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.nhz.doctorapp.mvp.presenters.MainPresenter
import com.nhz.doctorapp.mvp.views.MainView
import com.nhz.shared.mvp.presenters.AbstractBasePresenter

class MainPresenterImpl : AbstractBasePresenter<MainView>(),MainPresenter {
    override fun onUiReady(context: Context, lifecycleOwner: LifecycleOwner) {
        getPatientInfo()
        getSpecialities()
    }

    private fun getPatientInfo(){
        //userId
        mModel.getPatientByPatientIdAndSaveToDatabase(mAuthModel.getUserToken(),{},{})
    }

    private fun getSpecialities(){
        mModel.getAllSpecialitiesAndSaveToDatabase({},{})
    }
}