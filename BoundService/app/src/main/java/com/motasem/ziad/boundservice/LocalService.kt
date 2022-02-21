package com.motasem.ziad.boundservice

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*


class LocalService : Service() {

    private val mBinder = LocalBinder()

    inner class LocalBinder : Binder() {
        fun getService(): LocalService {
            return this@LocalService
        }
    }

    override fun onCreate() {
        Toast.makeText(this, "Service onCreate", Toast.LENGTH_SHORT).show()
        super.onCreate()
    }

    override fun onBind(intent: Intent): IBinder {
        Toast.makeText(this, "Service onBind", Toast.LENGTH_SHORT).show()
        return mBinder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Toast.makeText(this, "Service onUnbind", Toast.LENGTH_SHORT).show()
        return super.onUnbind(intent)
    }

    override fun onRebind(intent: Intent?) {
        Toast.makeText(this, "Service onRebind", Toast.LENGTH_SHORT).show()
        super.onRebind(intent)
    }

    override fun onDestroy() {
        Toast.makeText(this, "Service onDestroy", Toast.LENGTH_SHORT).show()
        super.onDestroy()
    }

    fun getCurrentTime(): String {
        val dateFormat = SimpleDateFormat("HH:mm:ss dd/MM/yyyy", Locale.US)
        return dateFormat.format(Date())
    }

 /*   fun get   CounterDown(): Long {
        val countDownTimer: CountDownTimer = object : CountDownTimer(5000, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onFinish() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        }
    }

  */

}



