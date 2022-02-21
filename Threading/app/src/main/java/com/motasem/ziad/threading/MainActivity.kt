package com.motasem.ziad.threading

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val TAG = "mzn"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnStart.setOnClickListener {
            Thread {
                for (i in 1..5) {
                    Log.e(TAG, "i = $i")
                    Thread.sleep(1000)
                    Log.e(TAG, Thread.currentThread().name)
                    Log.e(TAG, Thread.currentThread().id.toString())
                }
                /*  runOnUiThread {
                      tvMsg.text = "Loop is finished"
                  } */
                // or
                /* tvMsg.post {
                     tvMsg.text = "Loop is finished"
                 } */
                // or
                //  tvMsg.postDelayed({ tvMsg.text = "Loop is finished" }, 3000)
                // or
                Handler().postDelayed({ tvMsg.text = "Loop is finished" }, 2000)


            }.start()
        }


        /* btnStart.setOnClickListener {

             Thread(Runnable {
                 while (true) {
                     Log.e(TAG, Thread.currentThread().name)
                     Log.e(TAG, Thread.currentThread().id.toString())
                 }
             }).start()

           */


    }


}

