package com.nhz.doctorapp.workers

import android.content.Context
import androidx.work.WorkerParameters

class Worker(context: Context,workerParameters: WorkerParameters) : BaseWorker(context,workerParameters) {
    override fun doWork(): Result {
        return Result.failure()
    }
}