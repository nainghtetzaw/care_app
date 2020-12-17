package com.nhz.doctorapp.delegates

interface ConsultationHistoryDelegate {

    fun onTapNote(consultationId : String,patientName : String,patientBd: String)
    fun onTapPrescription(consultationId : String)
}