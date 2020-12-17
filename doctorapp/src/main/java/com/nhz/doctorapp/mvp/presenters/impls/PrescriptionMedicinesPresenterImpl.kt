package com.nhz.doctorapp.mvp.presenters.impls

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.nhz.doctorapp.getCurrentDate
import com.nhz.doctorapp.getCurrentTime
import com.nhz.doctorapp.getTimeStamp
import com.nhz.doctorapp.mvp.presenters.interfaces.PrescriptionMedicinesPresenter
import com.nhz.doctorapp.mvp.views.PrescriptionMedicinesView
import com.nhz.shared.data.vos.ConsultationsVO
import com.nhz.shared.data.vos.DoctorVO
import com.nhz.shared.data.vos.LiveChatVO
import com.nhz.shared.data.vos.MedicinesVO
import com.nhz.shared.mvp.presenters.AbstractBasePresenter
import java.util.*

class PrescriptionMedicinesPresenterImpl : AbstractBasePresenter<PrescriptionMedicinesView>(),PrescriptionMedicinesPresenter {

    private var medicineList : MutableList<MedicinesVO> = mutableListOf()
    private var conId : String = ""
    private lateinit var doctor : DoctorVO

    override fun onUiReady(specialityId: Int,consultationId : String,context: Context, lifecycleOwner: LifecycleOwner) {
        conId = consultationId
        getMedicineList(specialityId)
        getDoctorInfo()
    }

    override fun addOldDataToMedicineList() {
        mView?.showMedicineList(medicineList)
    }

    override fun getOldData(): List<MedicinesVO> {
        return medicineList
    }

    override fun sendMessagePrescription() {
        mModel.getConsultationPrescriptionAndSaveToDatabase(conId,{
            val list = mutableListOf<String>()
            it.forEach {
                list.add(it.medicine)
            }
           mModel.sendMessage(conId, LiveChatVO(
                   sender_id = doctor.userId,
                   sender_name = doctor.name,
                   sender_image = doctor.profileImage,
                   medicineList = list,
                   timeStamp = getCurrentDate(),
                   time = getCurrentTime()
           ))
        },{})
    }

    override fun finishConsultation() {
        mModel.getUnfinishedConsultationFromNetworkByDoctorId(doctor.userId,false,{
            if (it.count() != 0){
                val consultation = it.filter { cons -> cons.accept }[0]
                mModel.addConsultation(ConsultationsVO(
                        consultation.id,
                        consultation.doctorId,
                        consultation.patientId,
                        consultation.doctor_info,
                        consultation.patient_info,
                        true,
                        consultation.accept
                ))
                mModel.addRecentDoctors(consultation.patientId,consultation.doctor_info!!)
            }
        },{})
    }

    override fun onAddMedicine(name: String,price : Int) {
        mView?.showPrescribeMedicineFragment(name,conId,price)
    }

    override fun onDeleteMedicine(name: String) {
        mModel.deleteMedicine(name,conId)
    }

    private fun getDoctorInfo(){
        mModel.getDoctorByDoctorIdAndSaveToDatabase("1234512345",{
            doctor = it
        },{})
    }

    private fun getMedicineList(specialityId : Int){
        mModel.getAllSpecialityMedicinesByIdAndSaveToDatabase(specialityId,{ medicines ->
            medicineList = medicines.toMutableList()
            mView?.showMedicineList(medicines) }
                ,{})
    }
}