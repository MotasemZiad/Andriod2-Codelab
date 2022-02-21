package com.motasem.ziad.lab5threadingasynktask

import android.os.Bundle
import android.os.Message
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val TAG = "mzn"
    lateinit var myThread: MyThread
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myThread = MyThread()

        btnStart.setOnClickListener {
            myThread.start()
        }
        btnStop.setOnClickListener {
            myThread.looper!!.quit()
        }
        btnTaskA.setOnClickListener {
            val msg: Message = Message.obtain()
            msg.what = MyHandler.TASK_A
            myThread.handler.sendMessage(msg)
//                myThread.handler.post {
//                    for (i in 1..5) {
//                        Log.e(TAG, "run $i")
//                        Thread.sleep(1000)
//                    }
//                }
        }
        btnTaskB.setOnClickListener {
            val msg: Message = Message.obtain()
            msg.what = MyHandler.TASK_B
            myThread.handler.sendMessage(msg)
        }

    }

}
