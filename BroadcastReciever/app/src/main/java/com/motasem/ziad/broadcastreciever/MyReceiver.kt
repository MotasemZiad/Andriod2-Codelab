package com.motasem.ziad.broadcastreciever

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class MyReceiver : BroadcastReceiver() {


    override fun onReceive(context: Context?, intent: Intent?) {
        when {
            intent!!.action.equals(Intent.ACTION_BATTERY_LOW) ->
                Toast.makeText(context, "ACTION_BATTERY_LOW", Toast.LENGTH_SHORT).show()
            intent.action.equals(Intent.ACTION_POWER_CONNECTED) ->
                Toast.makeText(context, "ACTION_POWER_CONNECTED", Toast.LENGTH_SHORT).show()
            intent.action.equals(Intent.ACTION_POWER_DISCONNECTED) ->
                Toast.makeText(context, "ACTION_POWER_DISCONNECTED", Toast.LENGTH_SHORT).show()
        }
    }


}
