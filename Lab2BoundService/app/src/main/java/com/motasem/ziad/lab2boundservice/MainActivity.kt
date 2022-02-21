package com.motasem.ziad.lab2boundservice

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var serviceConnection: ServiceConnection
    var generatedRandomNumberService: GeneratedRandomNumberService? = null
    var isBound: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        serviceConnection = object : ServiceConnection {

            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                val binder: GeneratedRandomNumberService.GenerateBindService =
                    service as GeneratedRandomNumberService.GenerateBindService
                generatedRandomNumberService = binder.generatedBinderService
            }

            override fun onServiceDisconnected(name: ComponentName?) {
                generatedRandomNumberService = null
            }

        }

        btnBind.setOnClickListener(this)
        btnUnbind.setOnClickListener(this)
        btnGenerate.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btnBind -> {
                bindService(
                    Intent(this, GeneratedRandomNumberService::class.java),
                    serviceConnection,
                    Context.BIND_AUTO_CREATE
                )
                isBound = true
            }
            R.id.btnUnbind -> {
                if (isBound)
                    unbindService(serviceConnection)
                else
                    Toast.makeText(this, "it’s already unbind", Toast.LENGTH_SHORT).show()
            }
            R.id.btnGenerate -> {
                edNumber.setText(generatedRandomNumberService!!.number.toString())
            }
        }
    }
}
