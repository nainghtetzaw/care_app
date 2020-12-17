package com.nhz.doctorapp.mvp.presenters.impls

import com.nhz.doctorapp.mvp.presenters.OrderMedicinePresenter
import com.nhz.doctorapp.mvp.views.OrderMedicineView
import com.nhz.shared.data.vos.CheckOutVO
import com.nhz.shared.data.vos.PrescriptionVO
import com.nhz.shared.mvp.presenters.AbstractBasePresenter

class OrderMedicinePresenterImpl : AbstractBasePresenter<OrderMedicineView>(),OrderMedicinePresenter {

    private var totalPrice : Int = 0
    private var prescriptionList : MutableList<PrescriptionVO> = mutableListOf()

    override fun onUiReady(consultationId: String) {
        getConsultationPrescription(consultationId)
    }

    override fun addCheckOut(address : String) {
        mModel.getPatientByPatientIdAndSaveToDatabase("72JXNg3bVUZ0FRyanMNiNm2WLPn1",{patient ->
            mModel.checkOut(patient.userId, CheckOutVO(
                patient = patient,
                address = address,
                total_price = totalPrice,
            ))
            prescriptionList.forEach{
                mModel.checkOutPrescription(patient.userId,it)
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