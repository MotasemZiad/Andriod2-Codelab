package com.motasem.ziad.assignment2

import android.app.*
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.provider.Settings.Global.getString
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService

class MyReceiver : BroadcastReceiver() {
    lateinit var myNotificationManager: MyNotificationManager
    override fun onReceive(context: Context?, intent: Intent?) {
        myNotificationManager = MyNotificationManager(context!!)
        val i = Intent(context, MainActivity::class.java)
        i.putExtra("msg", "Your Battery level low, You have to charge it.")
        when {
            intent!!.action.equals(Intent.ACTION_BATTERY_LOW) -> {
                myNotificationManager.showNotification(
                    1,
                    "Low Battery!!",
                    "Your Battery level low, You have to charge it.",
                    intent
                )
                val alertDialog = AlertDialog.Builder(context)
                alertDialog.setTitle("Low Battery!!")
                alertDialog.setMessage("Your Battery level low, You have to charge it.")
                alertDialog.setCancelable(false)
                alertDialog.setIcon(R.drawable.ic_warning)

                alertDialog.setPositiveButton("OK") { _, _ ->
                    Toast.makeText(context, "Ok", Toast.LENGTH_SHORT).show()
                }
                alertDialog.create().show()
            }


        }
    }


}