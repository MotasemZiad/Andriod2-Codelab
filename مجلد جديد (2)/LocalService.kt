package com.h.alrekhawi.boundserviceiugexample

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class LocalService : Service() {

    val mBinder = LocalBinder()

    inner class LocalBinder : Binder() {
        fun getService(): LocalService {
            return this@LocalService
        }
    }

    override fun onBind(intent: Intent): IBinder {
        Toast.makeText(this, "Service onBind", Toast.LENGTH_SHORT).show()
        return mBinder
    }

    override fun onCreate() {
        super.onCreate()
        Toast.makeText(this, "Service Created", Toast.LENGTH_SHORT).show()
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Toast.makeText(this, "Service UnBind", Toast.LENGTH_SHORT).show()
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this, "Service Destroyed", Toast.LENGTH_SHORT).show()
    }

    fun getCurrentTime(): String {
        val simpleDate = SimpleDateFormat("HH:mm:ss MM/dd/yyyy", Locale.US)
        return simpleDate.format(Date())
    }
}
