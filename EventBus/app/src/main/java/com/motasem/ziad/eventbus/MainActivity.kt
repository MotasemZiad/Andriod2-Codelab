package com.motasem.ziad.eventbus

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnStartService.setOnClickListener {
            startService(Intent(this, GenerateRandomService::class.java))
        }
        btnStopService.setOnClickListener {
            stopService(Intent(this, GenerateRandomService::class.java))
        }

    }

    @Subscribe
    fun onMeesage(event: MessageEvent) {
        Toast.makeText(this, event.num.toString(), Toast.LENGTH_SHORT).show()
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }
}
