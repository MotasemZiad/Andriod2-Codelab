package com.motasem.ziad.lab6webservice

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    lateinit var progressDialog: ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        btnGetResponse.setOnClickListener {
            progressDialog = ProgressDialog(this)
            progressDialog.setMessage("جاري التحميل ..\nانتظر")
            progressDialog.setCancelable(false)
            getContacts()
        }

        btnGoToAct2.setOnClickListener {
            startActivity(Intent(this, Act2Activity::class.java))
        }

    }

    private fun getContacts() {
        progressDialog.show()
        val stringRequest =
            StringRequest(Request.Method.GET, URLs.GET_CONTACTS, Response.Listener { response ->
                progressDialog.dismiss()
                val jsonObject = JSONObject(response)
                tvPhone.text = jsonObject.getString("phone")
                tvEmail.text = jsonObject.getString("email")
            },
                Response.ErrorListener { error ->
                    Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
                })
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest)
    }
}
