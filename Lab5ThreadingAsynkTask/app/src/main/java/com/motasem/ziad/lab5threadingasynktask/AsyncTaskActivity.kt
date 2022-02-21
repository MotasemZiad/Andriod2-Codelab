package com.motasem.ziad.lab5threadingasynktask

import android.app.ProgressDialog
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import coil.api.load
import coil.transform.CircleCropTransformation
import kotlinx.android.synthetic.main.activity_async_task.*
import java.net.HttpURLConnection
import java.net.URL

class AsyncTaskActivity : AppCompatActivity() {
    lateinit var progressDialog: ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_async_task)

//        btnDownload.setOnClickListener {
//            //MyAsyncTask().execute("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcRJAerYf3EG1AZVJTW5owBUonbM0vJkXDo110abBKunQa9GpVg8&usqp=CAU")
//            MyAsyncTask().execute("https://www.kinolumiere.com/uploads/films/1583395174_wallpapersden.com_onward-2020-movie_1920x1080.jpg")
//        }

        btnDownload.setOnClickListener {
            img.load("https://www.kinolumiere.com/uploads/films/1583395174_wallpapersden.com_onward-2020-movie_1920x1080.jpg") {
                placeholder(R.drawable.ic_download)
            }
        }

    }

    inner class MyAsyncTask : AsyncTask<String?, String?, Bitmap>() {


        override fun onPreExecute() {
            super.onPreExecute()
            progressDialog = ProgressDialog(this@AsyncTaskActivity)
            progressDialog.setMessage("start downloading the image\nPlease wait ..")
            progressDialog.setCancelable(false)
            progressDialog.show()

        }

        override fun doInBackground(vararg params: String?): Bitmap {
            val imgURL = URL(params[0])
            val connection = imgURL.openConnection() as HttpURLConnection
            connection.connect()
            val inputStream = connection.inputStream
            val bitmapImg = BitmapFactory.decodeStream(inputStream)
            return bitmapImg
        }

        override fun onPostExecute(result: Bitmap?) {
            super.onPostExecute(result)
            img.setImageBitmap(result)
            progressDialog.dismiss()
        }


    }


}
