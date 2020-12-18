package com.nhz.doctorapp.delegates

import com.nhz.shared.data.vos.ConsultationRequestVO
import com.nhz.shared.data.vos.PatientVO

interface HomeDelegate {

    fun onTapAcceptRequest(request : ConsultationRequestVO)
    fun onTapRemoveRequest(id : String)
    fun onTapSelectTime()

    fun onTapMedication(consultationId : String,patientId : String)
    fun onTapPrescription(consultationId: String)
    fun onTapNote(consultationId: String,patientName : String,patientBd : String)

}