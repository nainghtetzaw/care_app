package com.nhz.shared.mvp.presenters

import androidx.lifecycle.ViewModel
import com.nhz.shared.data.models.CareAppAuthModel
import com.nhz.shared.data.models.CareAppAuthModelImpl
import com.nhz.shared.data.models.CareAppModel
import com.nhz.shared.data.models.CareAppModelImpl
import com.nhz.shared.mvp.views.BaseView

abstract class AbstractBasePresenter<T : BaseView> : BasePresenter<T>,ViewModel() {

    val mModel : CareAppModel = CareAppModelImpl
    val mAuthModel : CareAppAuthModel = CareAppAuthModelImpl

    var mView : T ?= null

    override fun initPresenter(view: T) {
        mView = view
    }

}