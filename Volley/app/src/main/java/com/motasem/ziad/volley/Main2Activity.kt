package com.motasem.ziad.volley

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PowerManager
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.motasem.ziad.volley.adapter.PostAdapter
import com.motasem.ziad.volley.model.Post
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main2.*
import java.io.UnsupportedEncodingException
import java.nio.charset.Charset

class Main2Activity : AppCompatActivity() {
    val URL_ARR = "https://jsonplaceholder.typicode.com/posts"
    val TAG = "mzn"
    lateinit var posts: ArrayList<Post>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        posts = ArrayList()
        getJSONArray()
    }

    private fun getJSONArray() {

        val jsonArrayRequest = JsonArrayRequest(
            Request.Method.GET, URL_ARR, null,
            Response.Listener { response ->
                for (i in 0 until response.length()) {
                    posts.add(
                        Post(
                            response.getJSONObject(i).getString("userId"),
                            response.getJSONObject(i).getString("id"),
                            response.getJSONObject(i).getString("title"),
                            response.getJSONObject(i).getString("body")
                        )
                    )
                }
                rvData.layoutManager = LinearLayoutManager(this)
                rvData.setHasFixedSize(true)
                rvData.adapter = PostAdapter(this, posts)
            },
            Response.ErrorListener { error ->
                Log.e(TAG, error.message!!)
            })
        MySingleton.getInstance()!!.addToRequestQueue(jsonArrayRequest, "JsonArray")

// Caching
        /*  val cache = MySingleton.getInstance()!!.getRequestQueue()!!.cache
          val entry = cache.get(URL_ARR)
          if (entry != null) {
              try {
                  val data = String(entry.data, Charset.forName("UTF-8"))
                  // handle data
              } catch (e: UnsupportedEncodingException) {
                  Log.e(TAG, e.message.toString())
              }
          } else {
              Log.e(TAG, "No cache for this request!")
          }

         */
    }
}
