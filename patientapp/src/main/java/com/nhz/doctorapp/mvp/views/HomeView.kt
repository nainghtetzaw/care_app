package com.nhz.doctorapp.mvp.views

import com.nhz.shared.data.vos.DoctorVO
import com.nhz.shared.data.vos.SpecialitiesVO
import com.nhz.shared.mvp.views.BaseView

interface HomeView : BaseView {

    fun showConfirmDialog(specialitId : Int,specialityType : String,oldOrNew : Boolean)
    fun showSpecialitiesData(data : List<SpecialitiesVO>)
    fun showRecentDoctorData(data : List<DoctorVO>)
    fun showConsultationAcceptedDoctor(doctor : DoctorVO)
    fun networkError(error : String)

    fun hideAcceptedRequestViewPod()
    fun hideRecentDoctorList()
    fun showAcceptedRequestViewPod()
    fun showRecentDoctorList()
}