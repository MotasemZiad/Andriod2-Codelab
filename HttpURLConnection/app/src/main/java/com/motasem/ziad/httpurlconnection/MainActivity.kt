package com.motasem.ziad.httpurlconnection

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    val URL = "https://api.androidhive.info/volley/person_object.json"
    lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnStart.setOnClickListener {
            GetResponseAsyncTask().execute(URL)
        }

    }

    inner class GetResponseAsyncTask : AsyncTask<String, Int, ArrayList<String>>() {

        override fun onPreExecute() {
            super.onPreExecute()
            progressDialog = ProgressDialog(this@MainActivity)
            progressDialog.setMessage("Loading data ...")
            progressDialog.setCancelable(false)
            progressDialog.show()

        }

        override fun doInBackground(vararg params: String?): ArrayList<String> {
            val data = ArrayList<String>()
            val response = HttpHandler.makeServiceCall(params[0])
            Log.e("mzn", response)

            val jsonObject = JSONObject(response)
            val name = jsonObject.getString("name")
            val email = jsonObject.getString("email")

            val phone = jsonObject.getJSONObject("phone")
            val home = phone.getString("home")
            val mobile = phone.getString("mobile")

            data.add(name)
            data.add(email)
            data.add(mobile)
            data.add(home)

            return data
        }

        @SuppressLint("SetTextI18n")
        override fun onPostExecute(result: ArrayList<String>) {
            super.onPostExecute(result)
            edName.setText("Name: ${result[0]}")
            edEmail.setText("Email: ${result[1]}")
            edMobile.setText("Mobile: ${result[2]}")
            edHomePhone.setText("Home phone: ${result[3]}")
            if (progressDialog.isShowing)
                progressDialog.dismiss()
        }

    }
}
