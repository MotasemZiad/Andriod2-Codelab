package com.motasem.ziad.assignmentvolley

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.motasem.ziad.assignmentvolley.adapter.CaseAdapter
import com.motasem.ziad.assignmentvolley.model.Case
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val URL = "https://corona.ps/API/cases"
    lateinit var progressDialog: ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Loading ,,,")
        progressDialog.setCancelable(false)
        getCovidData()
    }

    private fun getCovidData() {
        progressDialog.show()
        val list = ArrayList<Case>()
        val jsonObjectRequest =
            JsonObjectRequest(Request.Method.GET, URL, null, Response.Listener { response ->
                progressDialog.dismiss()
                val dataObject = response.getJSONObject("data")
                val casesArray = dataObject.getJSONArray("cases")
                for (i in 0 until casesArray.length()) {
                    list.add(
                        Case(
                            casesArray.getJSONObject(i).getString("case_number"),
                            casesArray.getJSONObject(i).getString("case_age"),
                            casesArray.getJSONObject(i).getString("case_gender"),
                            casesArray.getJSONObject(i).getString("case_location"),
                            casesArray.getJSONObject(i).getString("case_diagnose_date"),
                            casesArray.getJSONObject(i).getString("case_source_of_infection"),
                            casesArray.getJSONObject(i).getString("case_condition"),
                            casesArray.getJSONObject(i).getString("case_quarantine"),
                            casesArray.getJSONObject(i).getString("case_community")
                        )
                    )
                }
                val caseAdapter = CaseAdapter(this, list)
                rvData.layoutManager = LinearLayoutManager(this)
                rvData.setHasFixedSize(true)
                rvData.adapter = caseAdapter
            },
                Response.ErrorListener { error ->
                    Log.e("mzn", error.message!!)
                    Toast.makeText(this, error.message, Toast.LENGTH_LONG).show()
                })

        VolleySingleton.getInstance()!!.addToRequestQueue(jsonObjectRequest)


    }

}
