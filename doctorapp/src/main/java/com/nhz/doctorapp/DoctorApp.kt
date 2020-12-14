package com.nhz.doctorapp

import android.app.Application
import com.nhz.shared.data.models.CareAppModelImpl

class DoctorApp : Application() {

    override fun onCreate() {
        super.onCreate()
        CareAppModelImpl.getDatabaseInstance(applicationContext)
    }

}