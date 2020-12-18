package com.nhz.doctorapp.mvp.presenters.impls

import android.content.Context
import android.widget.Toast
import com.nhz.doctorapp.getCurrentDate
import com.nhz.doctorapp.getCurrentTime
import com.nhz.doctorapp.getTimeStamp
import com.nhz.doctorapp.mvp.presenters.interfaces.SpecialityQuestionsPresenter
import com.nhz.doctorapp.mvp.views.SpecialityQuestionsView
import com.nhz.shared.data.vos.DoctorVO
import com.nhz.shared.data.vos.LiveChatVO
import com.nhz.shared.mvp.presenters.AbstractBasePresenter
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.*

class SpecialityQuestionsPresenterImpl : AbstractBasePresenter<SpecialityQuestionsView>(),SpecialityQuestionsPresenter {

    private lateinit var doctor : DoctorVO
    private var conId : String = ""

    override fun onUiReady(specialityId: Int, consultationId: String) {
        conId = consultationId
        getDoctorData()
        getSpecialityQuestions(specialityId)
    }

    override fun onTapItem(question: String,context: Context) {
        mModel.sendMessage(conId, LiveChatVO(
                message = question,
                sender_id = doctor.userId,
                sender_name = doctor.name,
                sender_image = doctor.profileImage,
                timeStamp = getCurrentDate(),
                time = getCurrentTime()
        ))
        Toast.makeText(context,"Message sent",Toast.LENGTH_SHORT).show()
    }

    private fun getSpecialityQuestions(specialityId: Int){
        mModel.getAllSpecialityQuestionsByIdAndSaveToDatabase(specialityId,{
            mView?.showQuestions(it)
        },{})
    }

    private fun getDoctorData(){
        mModel.getDoctorByDoctorIdAndSaveToDatabase(mAuthModel.getUserToken(),{
            doctor = it
        },{})
    }

}