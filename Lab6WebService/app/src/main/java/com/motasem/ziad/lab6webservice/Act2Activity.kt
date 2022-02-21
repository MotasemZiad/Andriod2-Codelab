package com.motasem.ziad.lab6webservice

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.motasem.ziad.lab6webservice.adapter.SocialAdapter
import com.motasem.ziad.lab6webservice.model.SocialData
import kotlinx.android.synthetic.main.activity_act2.*

class Act2Activity : AppCompatActivity(), SocialAdapter.onClick {
    lateinit var socialAdapter: SocialAdapter
    lateinit var list: ArrayList<SocialData>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_act2)
        list = ArrayList()
        getSocials()
    }

    private fun getSocials() {
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET,
            URLs.GET_SOCIALS,
            null,
            Response.Listener { response ->
                val jsonArray = response.getJSONArray("records")
                for (i in 0 until jsonArray.length()) {
                    list.add(
                        SocialData(
                            jsonArray.getJSONObject(i).getString("social_id"),
                            jsonArray.getJSONObject(i).getString("title"),
                            jsonArray.getJSONObject(i).getString("url"),
                            jsonArray.getJSONObject(i).getString("icon")
                        )
                    )
                }
                rvSocial.layoutManager = LinearLayoutManager(this)
                rvSocial.setHasFixedSize(true)
                socialAdapter = SocialAdapter(this, list, this)
                rvSocial.adapter = socialAdapter
            },
            Response.ErrorListener { error ->
                Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
            })

        VolleySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)

    }

    override fun onClickItem(position: Int) {
        val implicitIntent = Intent(Intent.ACTION_VIEW, Uri.parse(list[position].url))
        startActivity(implicitIntent)
    }
}
