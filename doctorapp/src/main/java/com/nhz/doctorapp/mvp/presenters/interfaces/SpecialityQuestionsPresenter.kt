package com.nhz.doctorapp.mvp.presenters.interfaces

import android.widget.BaseAdapter
import com.nhz.doctorapp.delegates.QuestionsDelegate
import com.nhz.doctorapp.mvp.views.SpecialityQuestionsView
import com.nhz.shared.mvp.presenters.BasePresenter

interface SpecialityQuestionsPresenter : BasePresenter<SpecialityQuestionsView>,QuestionsDelegate {

    fun onUiReady(specialityId : Int,consultationId : String)

}