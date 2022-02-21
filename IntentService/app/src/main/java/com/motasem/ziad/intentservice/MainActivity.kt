package com.motasem.ziad.intentservice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnStart.setOnClickListener{
            val i = Intent(this, MyIntentService::class.java)
            i.putExtra("msg", txtMsg.text.toString())
            if (txtMsg.text.isEmpty())
                Toast.makeText(this, "Fill Field", Toast.LENGTH_SHORT).show()
            else
            startService(i)
        }
    }
}
