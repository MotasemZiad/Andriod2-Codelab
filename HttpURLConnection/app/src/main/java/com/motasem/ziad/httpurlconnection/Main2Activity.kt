package com.motasem.ziad.httpurlconnection

import android.app.ProgressDialog
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity() {
    val URL = "https://api.androidhive.info/volley/person_array.json"
    lateinit var progressDialog: ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        btnStart.setOnClickListener {
            MyAsyncTask().execute(URL)
        }

    }

    inner class MyAsyncTask : AsyncTask<String, Int, String>() {
        override fun onPreExecute() {
            super.onPreExecute()
            progressDialog = ProgressDialog(this@Main2Activity)
            progressDialog.setCancelable(false)
            progressDialog.setMessage("Wait ..")
            progressDialog.show()
        }

        override fun doInBackground(vararg params: String?): String {
            val response = HttpHandler.makeServiceCall(params[0])
            Log.e("mzn", response)
            return response
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            tvMsg.text = result
            if (progressDialog.isShowing)
                progressDialog.dismiss()

        }

    }
}
