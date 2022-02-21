package com.motasem.ziad.assignment1

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import com.motasem.ziad.assignment1.db.DatabaseHelper
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignInAct : AppCompatActivity() {
    private lateinit var db: DatabaseHelper
    lateinit var progressDialog: ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        btnSignIn.setOnClickListener {


            if (edUsername.text.isEmpty())
                edUsername.error = "Fill this field"
            if (edPassword.text.isEmpty())
                edPassword.error = "Fill this field"

            if (edUsername.text.isNotEmpty() && edPassword.text.isNotEmpty()) {
                MyAsyncTask().execute(edUsername.text.toString(), edPassword.text.toString())
            }


        }


        tvSignUp.setOnClickListener {
            val i = Intent(this, SignUpAct::class.java)
            startActivity(i)
        }
    }

    inner class MyAsyncTask : AsyncTask<String?, String?, String?>() {
        override fun onPreExecute() {
            super.onPreExecute()
            progressDialog = ProgressDialog(this@SignInAct)
            progressDialog.setCancelable(false)
            progressDialog.setMessage("Verify User\nPlease wait ..")
            progressDialog.show()
        }

        @SuppressLint("WrongThread")
        override fun doInBackground(vararg params: String?): String? {
            createNotificationChannel()
            return "Verification completed"
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            progressDialog.dismiss()
        }

    }


    private fun createNotificationChannel() {
        db = DatabaseHelper(this)
        val CHANNEL_ID = "ServiceChannel"
        val manager: NotificationManager?
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                CHANNEL_ID, "Authentication Service Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(serviceChannel)
        } else {
            manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        }
        val notificationIntent = Intent(this, SignInAct::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0)


        val users = db.getAllUsers()

        for (u in users) {
            if (u.name.equals(edUsername.text.toString()) && u.password.equals(edPassword.text.toString())) {
                val notification = NotificationCompat.Builder(this, CHANNEL_ID)
                    .setContentTitle("Account Verification")
                    .setContentText("User correct")
                    .setSmallIcon(R.drawable.android_logo)
                    .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.ic_login))
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true)
                    .build()
                manager!!.notify(1, notification)
            } else {
                val notification = NotificationCompat.Builder(this, CHANNEL_ID)
                    .setContentTitle("Account Verification")
                    .setContentText("User incorrect!!")
                    .setSmallIcon(R.drawable.android_logo)
                    .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.ic_login))
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true)
                    .build()
                manager!!.notify(1, notification)
            }

        }


    }


}
