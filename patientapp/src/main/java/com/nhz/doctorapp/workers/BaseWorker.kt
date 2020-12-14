package com.nhz.doctorapp.workers

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.nhz.shared.data.models.CareAppModel
import com.nhz.shared.data.models.CareAppModelImpl

abstract class BaseWorker(context: Context,workerParameters: WorkerParameters) : Worker(context,workerParameters) {

    val mModel : CareAppModel = CareAppModelImpl

}