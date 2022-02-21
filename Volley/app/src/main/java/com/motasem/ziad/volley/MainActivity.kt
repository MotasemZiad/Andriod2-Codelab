package com.motasem.ziad.volley

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.RetryPolicy
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val TAG = "mzn"
    val URL_OBJ = "https://api.androidhive.info/volley/person_object.json"
    val URL_ARR = "https://api.androidhive.info/volley/person_array.json"
    val URL_STR = "http://www.google.com/"

    val URL_POST = "https://jsonplaceholder.typicode.com/posts"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnStringRequest.setOnClickListener {
            getStringRequest()
        }
        btnObjectRequest.setOnClickListener {
            getJSONObject()
        }
        btnArrayRequest.setOnClickListener {
            getJSONArray()
        }
        btnPost.setOnClickListener {
            postToServer()
        }
        btnGoToSecondActivity.setOnClickListener {
            startActivity(Intent(this, Main2Activity::class.java))
        }

    }


    private fun getStringRequest() {
        val queue = Volley.newRequestQueue(this)


        val stringRequest = StringRequest(Request.Method.GET, URL_STR,
            { response ->
                Log.e(TAG, response.substring(0, 50))
                tvData.text = response.substring(0, 50)
            },
            { error ->
                Log.e(TAG, error.message!!)
                tvData.text = error.message!!
            })
        queue.add(stringRequest)
    }

    private fun getJSONObject() {
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, URL_OBJ, null,
            Response.Listener { response ->
                Log.e(TAG, response.toString())
                tvData.text = response.toString()
            },
            Response.ErrorListener { error ->
                Log.e(TAG, error.message!!)
                tvData.text = error.message!!
            })

        MySingleton.getInstance()!!.addToRequestQueue(jsonObjectRequest)
    }

    private fun getJSONArray() {
        val jsonArrayRequest = JsonArrayRequest(Request.Method.GET, URL_ARR, null,
            Response.Listener { response ->
                Log.e(TAG, response.toString())
                tvData.text = response.toString()
            },
            Response.ErrorListener { error ->
                Log.e(TAG, error.message!!)
                tvData.text = error.message!!
            })
        MySingleton.getInstance()!!.addToRequestQueue(jsonArrayRequest, "JsonArray")
    }

    private fun postToServer() {
        val stringRequest =
            object : StringRequest(
                Method.POST, URL_POST, Response.Listener { response ->
                    Log.e(TAG, response.toString())
                },
                Response.ErrorListener { error ->
                    Log.e(TAG, error.message!!)
                }) {
                override fun getParams(): MutableMap<String, String> {
                    val map = HashMap<String, String>()
                    map["title"] = "firstJson"
                    map["body"] = "HelloWorld!"
                    map["userId"] = "2"
                    return map
                }

                override fun getHeaders(): MutableMap<String, String> {
                    val map = HashMap<String, String>()
                    map["Content-Type"] = "application/json"
                    map["apiKey"] = "xxxxxxxxxxxx"
                    map["Access-Token"] = "ZZZZZZZZZZZZZZZZ"
                    return map
                }

                override fun getPriority(): Priority {
                    return Priority.HIGH
                }

                override fun getRetryPolicy(): RetryPolicy {
                    /*return DefaultRetryPolicy(
                        3000,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_TIMEOUT_MS.toFloat()
                    )
                     */
                    return DefaultRetryPolicy(10 * 1000, 2, 2F)
                }


            }
        MySingleton.getInstance()!!.addToRequestQueue(stringRequest)
        MySingleton.getInstance()!!.addToRequestQueue(stringRequest, "MZ")
        // stringRequest.cancel()
        //MySingleton.getInstance()!!.cancelPendingRequest("MZ")

    }

    override fun onStop() {
        super.onStop()
        MySingleton.getInstance()!!.cancelPendingRequest("MZ")
    }

}


