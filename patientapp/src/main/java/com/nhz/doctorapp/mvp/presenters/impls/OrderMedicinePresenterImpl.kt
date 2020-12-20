package com.nhz.doctorapp.mvp.presenters.impls

import com.nhz.doctorapp.mvp.presenters.OrderMedicinePresenter
import com.nhz.doctorapp.mvp.views.OrderMedicineView
import com.nhz.shared.data.vos.CheckOutVO
import com.nhz.shared.data.vos.PrescriptionVO
import com.nhz.shared.mvp.presenters.AbstractBasePresenter

class OrderMedicinePresenterImpl : AbstractBasePresenter<OrderMedicineView>(),OrderMedicinePresenter {

    private var totalPrice : Int = 0
    private var conId : String = ""
    private var prescriptionList : MutableList<PrescriptionVO> = mutableListOf()

    override fun onUiReady(consultationId: String) {
        conId = consultationId
        getConsultationPrescription(consultationId)
    }

    override fun addCheckOut(address : String) {
        mModel.getPatientByPatientIdAndSaveToDatabase(mAuthModel.getUserToken(),{patient ->
            mModel.checkOut(conId, CheckOutVO(
                patient = patient,
                address = address,
                total_price = totalPrice,
            ))
            prescriptionList.forEach{
                mModel.checkOutPrescription(conId,it)
            }
        },{})
    }

    private fun getConsultationPrescription(consultationId: String){
        mModel.getConsultationPrescriptionAndSaveToDatabase(consultationId,{ prescriptions ->
            prescriptionList = prescriptions.toMutableList()
            prescriptions.forEach {
                val subTotal = it.quantity * it.price.toInt()
                totalPrice += subTotal
            }
            mView?.showMedicineList(prescriptions)
            mView?.showTotalPrice(totalPrice)
        },{})
    }
}