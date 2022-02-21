package com.motasem.ziad.foregroundservice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnStartService.setOnClickListener {
            val i = Intent(this, MyService::class.java)
            i.putExtra("msg", getString(R.string.notification_msg))
            ContextCompat.startForegroundService(this, i)
        }


        btnStopService.setOnClickListener {
            val i = Intent(this, MainActivity::class.java)
            stopService(i)
        }
    }
}
