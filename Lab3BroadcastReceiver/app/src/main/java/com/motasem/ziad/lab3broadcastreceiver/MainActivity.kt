package com.motasem.ziad.lab3broadcastreceiver

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ConnectivityReceiver.ConnectionReceiverListener {
    private var snackbar: Snackbar? = null
    private val receiver = ConnectivityReceiver()


    private val broadcastReceiver = object : BroadcastReceiver() {

        override fun onReceive(context: Context?, intent: Intent?) {
            when (intent!!.action) {
                "MyRandomNumberBroadcast" -> {
                    val data = intent.getIntExtra("number", -1)
                    Toast.makeText(context, "Random number\n$data", Toast.LENGTH_SHORT)
                        .show()
                }
                else -> {
                    Toast.makeText(context, "There is no action", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnStartAlarm.setOnClickListener {
            if (edTime.text.isNotEmpty())
                startAlarm()
            else
                edTime.error = "Please fill this field"
        }

        btnStartService.setOnClickListener {
            startService(Intent(this, GenerateRandomService::class.java))
        }

        btnStopService.setOnClickListener {
            stopService(Intent(this, GenerateRandomService::class.java))
        }


        registerReceiver(
            receiver,
            IntentFilter((ConnectivityManager.CONNECTIVITY_ACTION))
        )

        snackbar = Snackbar.make(
            findViewById(R.id.root_view),
            "Network Disconnected",
            Snackbar.LENGTH_LONG
        )
        snackbar!!.duration = BaseTransientBottomBar.LENGTH_INDEFINITE

    }

    override fun onNetworkConnected(isConnected: Boolean) {
        showMessage(isConnected)
    }

    private fun showMessage(connected: Boolean) {
        if (!connected) {
            snackbar!!.show()
        } else {
            snackbar!!.dismiss()
        }
    }

    override fun onResume() {
        super.onResume()
        ConnectivityReceiver.connectionReceiverListener = this
        LocalBroadcastManager.getInstance(this)
            .registerReceiver(broadcastReceiver, IntentFilter("MyRandomNumberBroadcast"))
    }

    override fun onPause() {
        super.onPause()
        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }


    private fun startAlarm() {
        val time = edTime.text.toString().toInt()
        val i = Intent(this, MyReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(this, 0, i, 0)
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.set(
            AlarmManager.RTC_WAKEUP,
            System.currentTimeMillis() + (time * 1000),
            pendingIntent
        )

        Toast.makeText(this, "Alarm is set after $time seconds", Toast.LENGTH_SHORT).show()


    }

}
