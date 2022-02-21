package com.motasem.ziad.lab3broadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Vibrator
import android.provider.MediaStore
import android.provider.Settings
import android.widget.Toast

class MyReceiver : BroadcastReceiver() {
    lateinit var mp: MediaPlayer
    override fun onReceive(context: Context, intent: Intent) {

        Toast.makeText(context, "Your time is up", Toast.LENGTH_SHORT).show()
        val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        vibrator.vibrate(3000)

        mp = MediaPlayer.create(context, Settings.System.DEFAULT_ALARM_ALERT_URI)
        mp.isLooping = false
        mp.start()
    }
}
