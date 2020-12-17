package com.nhz.doctorapp.mvp.presenters.interfaces

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.nhz.doctorapp.mvp.views.ChatView
import com.nhz.shared.mvp.presenters.BasePresenter
import java.util.*

interface ChatPresenter : BasePresenter<ChatView>{

    fun onUiReady(patientId : String,consultationId : String,context : Context,lifecycleOwner: LifecycleOwner)
    fun sendMessage(consultationId: String,
                    message: String,
                    patientName : String,
                    patientId : String,
                    patientImage : String)
    fun navigateToMedicineList()
    fun navigateToSpecialityQuestionsActivity()
    fun navigateToMedicalHistoryActivity(patientName: String,patientBd : String)

}