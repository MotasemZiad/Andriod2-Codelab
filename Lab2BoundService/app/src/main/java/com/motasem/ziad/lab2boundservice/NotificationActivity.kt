package com.motasem.ziad.lab2boundservice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_notification.*

class NotificationActivity : AppCompatActivity() {
    lateinit var myNotificationManager: MyNotificationManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)


        myNotificationManager = MyNotificationManager(this)
        val intent = Intent(this, NotificationActivity::class.java)
        intent.putExtra("msg", "Hello World!")

        btnNotify.setOnClickListener {
            myNotificationManager.showNotification(
                1,
                "Lab 3",
                getString(R.string.notification_message),
                intent
            )
            if (intent.hasExtra("msg")) {
                tvMessage.text = intent.getStringExtra("msg")
            }
        }


    }
}
