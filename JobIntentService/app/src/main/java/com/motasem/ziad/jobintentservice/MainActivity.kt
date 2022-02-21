package com.motasem.ziad.jobintentservice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnStart.setOnClickListener {
            val i = Intent(this, MyJobIntentService::class.java)
            i.putExtra("msg", edText.text.toString())
            if (edText.text.isEmpty())
                Toast.makeText(this, "Fill Fields", Toast.LENGTH_SHORT).show()
            else
                MyJobIntentService.enqueueWork(this, i)


        }

    }
}
