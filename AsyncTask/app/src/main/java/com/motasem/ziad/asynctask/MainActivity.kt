package com.motasem.ziad.asynctask

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val TAG = "mzn"
    private var count = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        btnStart.setOnClickListener {
            count = 0
            progressBar.progress = 0
            DownloadAsyncTask().execute(100)
            // myFun(1, 2, 3, 4, 5)
        }

    }

    inner class DownloadAsyncTask : AsyncTask<Int, Int, String>() {
        override fun onPreExecute() {
            super.onPreExecute()
//            Log.e(TAG, "onPreExecute")
//            Log.e(TAG, "onPreExecute " + Thread.currentThread().name)

            tvOutput2.text = "start Downloading..."
            progressBar.visibility = View.VISIBLE
            tvOutput1.visibility = View.VISIBLE
        }

        override fun doInBackground(vararg params: Int?): String {
//            Log.e(TAG, "doInBackground")
//            Log.e(TAG, "doInBackground " + Thread.currentThread().name)
            while (count <= params[0]!!) {
                Thread.sleep(1000)
                publishProgress(count)
                count += 10
            }

            for (i in 1..10) {
                Thread.sleep(1000)
                publishProgress()
            }

            return "Completed"
        }

        override fun onProgressUpdate(vararg values: Int?) {
            super.onProgressUpdate(*values)
//            Log.e(TAG, "onProgressUpdate")
//            Log.e(TAG, "onProgressUpdate " + Thread.currentThread().name)
            progressBar.incrementProgressBy(10)
            tvOutput1.text = "${progressBar.progress}/100"
            tvOutput2.text = "Downloading...${values[0]}"
            progressBar.progress = values[0]!!
        }


        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
//            Log.e(TAG, "onPostExecute")
//            Log.e(TAG, "onPostExecute " + Thread.currentThread().name
            progressBar.visibility = View.INVISIBLE
            tvOutput2.visibility = View.INVISIBLE
            progressBar.progress = 0
            tvOutput1.text = "0/100"
            btnStart.text = "Re-download"

        }


    }

    fun myFun(vararg x: Int) {
        for (i in 1..x.size) {
            Log.e(TAG, "i = $i")
        }
    }
}
