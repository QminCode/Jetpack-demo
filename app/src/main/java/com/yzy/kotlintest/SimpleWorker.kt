package com.yzy.kotlintest

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

/**
 * @author: playboi_YzY
 * @date: 2022/10/20 17:51
 * @description:
 * @version:
 */
class SimpleWorker(context: Context,params: WorkerParameters):Worker(context,params) {
    //doWork()方法不会运行在主线程中，我们可以放心实现各种逻辑。
    override fun doWork(): Result {
        Log.d("SimpleWorker", "do work in SimpleWorker")
        return Result.success()
    }
}