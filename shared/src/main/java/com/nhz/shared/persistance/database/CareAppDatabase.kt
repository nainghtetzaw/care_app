package com.nhz.shared.persistance.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.nhz.shared.data.vos.*
import com.nhz.shared.persistance.daos.*

@Database(entities = [CaseSummaryVO::class,CheckOutVO::class,ConsultationRequestVO::class,ConsultationsVO::class,DoctorVO::class,GeneralQuestionsVO::class,
    LiveChatVO::class,MedicinesVO::class,PatientVO::class,PrescriptionVO::class,SpecialitiesVO::class,SpecialityQuestionsVO::class,MedicalHistoryVO::class],version = 26,exportSchema = false)
abstract class CareAppDatabase : RoomDatabase() {

    companion object {
        private var dbInstance : CareAppDatabase?= null
        private val DB_NAME = "Care App Database"

        fun getDatabaseInstance(context: Context) : CareAppDatabase {
            when(dbInstance){
                null -> {
                    dbInstance = Room.databaseBuilder(context, CareAppDatabase::class.java, DB_NAME)
                        .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                        .enableMultiInstanceInvalidation()
                        .build()
                }
            }
            return dbInstance!!
        }
    }

    abstract fun consultationCaseSummaryDao() : ConsultationCaseSummaryDao
    abstract fun checkOutDao() : CheckOutDao
    abstract fun consultationDao() : ConsultationDao
    abstract fun doctorDao() : DoctorDao
    abstract fun generalQuestionsDao() : GeneralQuestionsDao
    abstract fun liveChatDao() : LiveChatDao
    abstract fun medicinesDao() : MedicinesDao
    abstract fun patientDao() : PatientDao
    abstract fun prescriptionDao() : PrescriptionDao
    abstract fun specialitiesDao() : SpecialitiesDao
    abstract fun specialityQuestions() : SpecialityQuestionsDao
    abstract fun consultationRequestedPatientDao() : ConsultationRequestPatientDao
    abstract fun consultationPrescriptionDao() : ConsultationPrescriptionDao
    abstract fun recentDoctorDao() : RecentDoctorDao
    abstract fun patientGeneralAnswersDao() : PatientGeneralAnswersDao
    abstract fun requestedPatientCaseSummaryDao() : RequestedPatientCaseSummaryDao
    abstract fun medicalHistoryDao() : MedicalHistoryDao
}