package com.motasem.ziad.volley

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.motasem.ziad.volley.adapter.MoviesAdapter
import com.motasem.ziad.volley.model.Movies
import kotlinx.android.synthetic.main.activity_main4.*

class Main4Activity : AppCompatActivity() {
    val URL_ARR = "https://api.androidhive.info/json/movies.json"
    val TAG = "mzn"
    lateinit var data: ArrayList<Movies>
    lateinit var progressDialog: ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

        data = ArrayList()
        progressDialog = ProgressDialog(this)
        progressDialog.setCancelable(false)
        progressDialog.setMessage("Loading movies ,,,")
        progressDialog.show()
        getMovies()


    }

    private fun getMovies() {
        val jsonArrayRequest = JsonArrayRequest(Request.Method.GET, URL_ARR, null,
            Response.Listener { response ->
                if (progressDialog.isShowing)
                    progressDialog.dismiss()
                for (i in 0 until response.length()) {
                    data.add(
                        Movies(
                            response.getJSONObject(i).getString("title"),
                            response.getJSONObject(i).getString("image"),
                            response.getJSONObject(i).getDouble("rating"),
                            response.getJSONObject(i).getInt("releaseYear"),
                            response.getJSONObject(i).getJSONArray("genre").toString()
                        )
                    )
                }
                rvMovies.layoutManager = LinearLayoutManager(this)
                rvMovies.setHasFixedSize(true)
                rvMovies.adapter = MoviesAdapter(this, data)
            },
            Response.ErrorListener { error ->
                if (progressDialog.isShowing)
                    progressDialog.dismiss()
                Log.e(TAG, error.message.toString())
                Toast.makeText(this, error.message, Toast.LENGTH_LONG).show()
            })
        MySingleton.getInstance()!!.addToRequestQueue(jsonArrayRequest, "MoviesArray")
    }
}
