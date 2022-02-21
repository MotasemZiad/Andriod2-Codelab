package com.motasem.ziad.lab5threadingasynktask

import android.os.Handler
import android.os.Looper
import android.util.Log

class MyThread : Thread() {
    private val TAG = "mzn"
    lateinit var handler: Handler
    var looper: Looper? = null
    override fun run() {
        super.run()
        Log.e(TAG, "Thread Name: $name")
        Log.e(TAG, "ID: $id")
        Log.e(TAG, "isInterrupted: $isInterrupted")
        Log.e(TAG, "isAlive: $isAlive")
        Looper.prepare()
        looper = Looper.myLooper()
        handler = Handler()
        Looper.loop()

        Log.e(TAG, "Thread is finished")
    }
}