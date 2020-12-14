package com.nhz.shared.mvp.presenters

import com.nhz.shared.mvp.views.BaseView

interface BasePresenter<T : BaseView>  {

    fun initPresenter(view : T)

}