package com.motasem.ziad.intentservice

import android.app.IntentService
import android.content.Intent
import android.os.SystemClock
import android.util.Log

class MyIntentService : IntentService("IntentServiceExample") {
    val TAG = "mzn"
    override fun onCreate() {
        super.onCreate()
        Log.e(TAG, "onCreate")
    }
    override fun onHandleIntent(intent: Intent?) {
        Log.e(TAG, "onHandleIntent")
        val msg = intent!!.getStringExtra("msg")
        for (i in 1 ..10){
            Log.e(TAG, "$msg -> $i")
            SystemClock.sleep(1000)
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG, "onDestroy")
    }

}
