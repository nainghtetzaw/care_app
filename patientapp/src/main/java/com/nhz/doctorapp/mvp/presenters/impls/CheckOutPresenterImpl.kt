package com.nhz.doctorapp.mvp.presenters.impls

import com.nhz.doctorapp.mvp.presenters.CheckOutPresenter
import com.nhz.doctorapp.mvp.views.CheckOutView
import com.nhz.shared.mvp.presenters.AbstractBasePresenter

class CheckOutPresenterImpl : AbstractBasePresenter<CheckOutView>(),CheckOutPresenter {
    override fun onUiReady(consultationId : String) {
        mModel.getCheckOutPrescriptionFromNetwork(consultationId,{prescriptions ->
            mView?.showPrescriptionData(prescriptions)
            mModel.getCheckOutFromNetwork(mAuthModel.getUserToken(),{
                mView?.showAddressAndPrice(it.address,(it.total_price + 3000).toString(),it.total_price.toString())
            },{})
        },{})
    }

//    override fun finishCheckOut() {
//        mModel.
//    }


}