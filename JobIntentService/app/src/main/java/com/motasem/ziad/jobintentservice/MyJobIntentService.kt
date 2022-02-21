package com.motasem.ziad.jobintentservice

import android.content.Context
import android.content.Intent
import android.os.SystemClock
import android.util.Log
import androidx.core.app.JobIntentService


class MyJobIntentService : JobIntentService() {
    val TAG = "mzn"

    companion object {
        fun enqueueWork(context: Context, work: Intent) {
            enqueueWork(context, MyJobIntentService::class.java, 100, work)
        }
    }

    override fun onCreate() {
        super.onCreate()
        Log.e(TAG, "onCreate")
    }

    override fun onHandleWork(intent: Intent) {
        Log.e(TAG, "onHandleWork")
        val msg = intent.getStringExtra("msg")
        for(i in 0..9){
           if(isStopped) return
            SystemClock.sleep(1000)
            Log.e(TAG, "$msg => $i")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG, "onDestroy")
    }

    override fun onStopCurrentWork(): Boolean {
        Log.e(TAG, "onStopCurrentWork")
        return super.onStopCurrentWork()
    }


}
