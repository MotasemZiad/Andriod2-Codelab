package com.motasem.ziad.lab2boundservice

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.Handler
import android.os.HandlerThread
import android.os.IBinder
import android.widget.Toast

class GeneratedRandomNumberService : Service() {
    val binder: IBinder = GenerateBindService()
    var number: Int = -1
    lateinit var handlerThread: HandlerThread
    lateinit var handler: Handler

    inner class GenerateBindService : Binder() {
        var generatedBinderService: GeneratedRandomNumberService = this@GeneratedRandomNumberService
    }


    override fun onCreate() {
        super.onCreate()
        handlerThread = HandlerThread("GENERATE_RANDOM_NUMBER")
        handlerThread.start()
        handler = Handler(handlerThread.looper)
        handler.post {
            randomNumber()
        }
    }

    override fun onBind(intent: Intent?): IBinder? {
        Toast.makeText(this, "Service Bound", Toast.LENGTH_SHORT).show()
        return binder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Toast.makeText(this, "Service Unbound", Toast.LENGTH_SHORT).show()
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    var isGenerated: Boolean = false
    fun randomNumber() {
        isGenerated = true
        while (isGenerated) {
            Thread.sleep(1000)
            number = (Math.random() * 100).toInt()
        }
    }
}