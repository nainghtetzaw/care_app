package com.nhz.doctorapp.mvp.presenters.impls

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.nhz.doctorapp.mvp.presenters.GeneralQuestionsPresenter
import com.nhz.doctorapp.mvp.views.GeneralQuestionsView
import com.nhz.shared.data.vos.CaseSummaryVO
import com.nhz.shared.mvp.presenters.AbstractBasePresenter

class GeneralQuestionsPresenterImpl : AbstractBasePresenter<GeneralQuestionsView>(),GeneralQuestionsPresenter {
    override fun onUiReady(context: Context, lifecycleOwner: LifecycleOwner) {
        getGeneralQuestionList(lifecycleOwner)
        getGeneralQuestionsAndAnswers(lifecycleOwner)
    }

    override fun sendGeneralAnswers(data: List<CaseSummaryVO>) {
        data.forEach {
            mModel.addPatientGeneralAnswers(mAuthModel.getUserToken(),it)
        }
    }

    override fun sendQuestionAndAnswer(id: Int, question: String, answer: String, oneTime: Boolean) {
//        //userid
//        mModel.addPatientGeneralAnswers("72JXNg3bVUZ0FRyanMNiNm2WLPn1", CaseSummaryVO(id,question, answer,oneTime))
    }

    private fun getGeneralQuestionsAndAnswers(lifecycleOwner: LifecycleOwner){
        //userid
        mModel.getPatientGeneralAnswersAndSaveToDatabase(mAuthModel.getUserToken(),{
            val oneTimeAns = it.filter { case -> case.one_time }
            if (oneTimeAns.isNotEmpty()){
                mView?.showQuestionsAndAnswersList()
                mView?.showOneTimeQuestionsAndAnswers(oneTimeAns)
                mModel.getAlwaysGeneralQuestionsFromNetwork({ always ->
                    mView?.showAlwaysQuestions(always)
                }, {})
            }else {
                mView?.hideQuestionsAndAnswersList()
                mModel.getAllGeneralQuestionsListAndSaveToDatabase({
                    mView?.showAlwaysQuestions(it)
                },{})
            }
        },{})
//        mModel.getPatientGeneralAnswersFromDatabaseByBoolean(true).observe(lifecycleOwner, Observer {
//            if (it.isNotEmpty()){
//                mView?.showQuestionsAndAnswersList()
//                mView?.showOneTimeQuestionsAndAnswers(it)
//                mModel.getGeneralQuestionListFromDatabaseByBoolean(false).observe(lifecycleOwner, Observer { alwaysData ->
//                    mView?.showAlwaysQuestions(alwaysData)
//                })
//            }else {
//                mView?.hideQuestionsAndAnswersList()
//                mModel.getGeneralQuestionListFromDatabase().observe(lifecycleOwner, Observer {
//                    mView?.showAlwaysQuestions(it)
//                })
//            }
//        })
    }

    private fun getGeneralQuestionList(lifecycleOwner: LifecycleOwner){
        mModel.getAllGeneralQuestionsListAndSaveToDatabase({},{})
    }


}