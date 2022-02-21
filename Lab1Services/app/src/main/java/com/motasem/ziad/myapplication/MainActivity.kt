package com.motasem.ziad.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnStart.setOnClickListener(this)
        btnStop.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btnStart -> {
                val i = Intent(this, MyService::class.java)
                i.putExtra("msg", "Sending data!")
                startService(i)
            }
            R.id.btnStop -> {
                val i = Intent(this, MyService::class.java)
                stopService(i)
            }
        }
    }
}
