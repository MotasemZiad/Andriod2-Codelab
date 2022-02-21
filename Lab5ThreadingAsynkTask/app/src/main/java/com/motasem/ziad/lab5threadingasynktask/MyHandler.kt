package com.motasem.ziad.lab5threadingasynktask

import android.os.Handler
import android.os.Message
import android.util.Log

class MyHandler : Handler() {
    private val TAG = "mzn"
    override fun handleMessage(msg: Message) {
        super.handleMessage(msg)
        when (msg.what) {
            TASK_A -> Log.e(TAG, "This is from Task A: $msg")
            TASK_B -> Log.e(TAG, "This is from Task B: $msg")
        }
    }


    companion object {
        val TASK_A = 1
        val TASK_B = 2
    }
}