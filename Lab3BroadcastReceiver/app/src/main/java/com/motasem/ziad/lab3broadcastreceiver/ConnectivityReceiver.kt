package com.motasem.ziad.lab3broadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager

class ConnectivityReceiver : BroadcastReceiver() {


    override fun onReceive(context: Context?, intent: Intent?) {

        if (connectionReceiverListener != null) {
            connectionReceiverListener!!.onNetworkConnected(checkConnection(context!!))
        }
    }

    private fun checkConnection(context: Context): Boolean {

        val connectManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectManager.activeNetworkInfo

        return networkInfo != null && networkInfo.isConnectedOrConnecting
    }

    interface ConnectionReceiverListener {
        fun onNetworkConnected(isConnected: Boolean)
    }

    companion object {
        var connectionReceiverListener: ConnectionReceiverListener? = null
    }

}