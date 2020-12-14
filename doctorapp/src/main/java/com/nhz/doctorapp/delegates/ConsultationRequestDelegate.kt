package com.nhz.doctorapp.delegates

import com.nhz.shared.data.vos.ConsultationRequestVO
import com.nhz.shared.data.vos.PatientVO

interface ConsultationRequestDelegate {

    fun onTapAcceptRequest(request : ConsultationRequestVO)

}