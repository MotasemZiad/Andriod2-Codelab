package com.motasem.ziad.assignment2

import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private val myReceiver = MyReceiver()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val intentFilter = IntentFilter()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            intentFilter.addAction(Intent.ACTION_BATTERY_LOW)
            registerReceiver(myReceiver, intentFilter)
        }
    }

    /*  private fun createNotificationChannel() {
          val CHANNEL_ID = "ServiceChannelExample"
          var manager: NotificationManager? = null
          if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
              val serviceChannel = NotificationChannel(
                  CHANNEL_ID, "Example Service Channel",
                  NotificationManager.IMPORTANCE_DEFAULT
              )
              manager = getSystemService(NotificationManager::class.java)
              manager.createNotificationChannel(serviceChannel)
          } else {
              manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
          }
          val notificationIntent = Intent(this, MainActivity::class.java)
          val pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0)

          val notification = NotificationCompat.Builder(this, CHANNEL_ID)
              .setContentTitle("Low Battery!!")
              .setContentText("Your Battery level low, You have to charge it.")
              .setSmallIcon(R.drawable.ic_error)
              .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.ic_warning))
              .setContentIntent(pendingIntent)
              .setAutoCancel(true)
              .build()

          manager!!.notify(1, notification)
      }
  */

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(myReceiver)
    }
}
