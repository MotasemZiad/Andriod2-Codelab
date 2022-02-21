package com.motasem.ziad.broadcastreciever

import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val myReceiver = MyReceiver()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val intentFilter = IntentFilter()
            intentFilter.addAction(Intent.ACTION_POWER_CONNECTED)
            intentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED)
            intentFilter.addAction(Intent.ACTION_BATTERY_LOW)
            registerReceiver(myReceiver, intentFilter)
        }

        btnSend.setOnClickListener {
            val i = Intent("com.motasem.ziad.broadcastreciever")
            i.putExtra("com.motasem.ziad.broadcastreciever.msg", "Hello from Sender Broadcast")
            sendBroadcast(i)
            Toast.makeText(this, "Sending a broadcast ..", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(myReceiver)
    }
}
