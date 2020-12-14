package com.nhz.shared.data.models

import android.content.Context
import com.nhz.shared.network.FirebaseApi
import com.nhz.shared.network.FirebaseApiImpl
import com.nhz.shared.network.auth.FirebaseAuthApi
import com.nhz.shared.network.auth.FirebaseAuthApiImpl
import com.nhz.shared.persistance.database.CareAppDatabase

abstract class BaseModel {

    protected lateinit var mDatabase : CareAppDatabase
    val mFirebaseApi : FirebaseApi = FirebaseApiImpl
    val mFirebaseAuthApi : FirebaseAuthApi = FirebaseAuthApiImpl

    fun getDatabaseInstance(context: Context){
        mDatabase = CareAppDatabase.getDatabaseInstance(context)
    }

}