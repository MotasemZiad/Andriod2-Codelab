package com.motasem.ziad.myapplication

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.CountDownTimer
import android.os.IBinder
import android.provider.Settings
import android.util.Log
import android.widget.Toast

class MyService : Service() {
   // lateinit var mediaPlayer: MediaPlayer
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        // mediaPlayer = MediaPlayer.create(this, Settings.System.DEFAULT_NOTIFICATION_URI)
        // mediaPlayer = MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI)
        // mediaPlayer = MediaPlayer.create(this, Settings.System.DEFAULT_ALARM_ALERT_URI)
        // mediaPlayer = MediaPlayer.create(this, R.raw.khawater)
        Toast.makeText(this, "Service Create", Toast.LENGTH_SHORT).show()
        // mediaPlayer.isLooping = true
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {


        val s = intent!!.getStringExtra("msg")
        Toast.makeText(applicationContext, "Receiving Intent => $s", Toast.LENGTH_SHORT).show()
        object : CountDownTimer(5000, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                Log.e("mzn", "Seconds remaining => ${millisUntilFinished / 1000}")
            }

            override fun onFinish() {
                Toast.makeText(applicationContext, "Self Stopping", Toast.LENGTH_SHORT).show()
                stopSelf()
            }

        }.start()

        /*   var i = 0
          while (i<=3){
              Thread.sleep(10000)
              i++
              Log.e("mzn", i.toString())
          }
        */

        // mediaPlayer.start()
        Toast.makeText(this, "Service Start", Toast.LENGTH_SHORT).show()
        //return START_STICKY
        // return START_NOT_STICKY
        return START_REDELIVER_INTENT
    }

    override fun onDestroy() {
       // mediaPlayer.stop()
        Toast.makeText(this, "Service Stop", Toast.LENGTH_SHORT).show()
        super.onDestroy()
    }
}