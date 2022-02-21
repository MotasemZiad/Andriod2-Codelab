package com.motasem.ziad.boundservice

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    var localService: LocalService? = null
    var isBound = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnBind.setOnClickListener(this)
        btnUnbind.setOnClickListener(this)
        btnGetDate.setOnClickListener(this)


    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btnBind -> {
                val i = Intent(this, LocalService::class.java)
                bindService(i, myConnection, Context.BIND_AUTO_CREATE)
            }
            R.id.btnGetDate -> {
                if (isBound) {
                    val time = localService!!.getCurrentTime()
                    edDate.setText(time)
                }
            }
            R.id.btnUnbind -> {
                unbindService(myConnection)
                isBound = false
            }
        }
    }

    private val myConnection = object : ServiceConnection {

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as LocalService.LocalBinder
            localService = binder.getService()
            isBound = true
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            isBound = false
        }

    }

}
