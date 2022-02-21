package com.motasem.ziad.assignmentlab6

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.motasem.ziad.assignmentlab6.adapter.ToDoAdapter
import com.motasem.ziad.assignmentlab6.model.ToDo
import com.motasem.ziad.assignmentlab6.model.ToDoData
import com.motasem.ziad.volley.MySingleton
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var progressDialog: ProgressDialog
    lateinit var data: ArrayList<ToDoData>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        data = ArrayList()
        progressDialog = ProgressDialog(this)
        progressDialog.setCancelable(false)
        progressDialog.setMessage("Loading ..")
        getData()
    }

    private fun getData() {
        val jsonArrayRequest = JsonArrayRequest(
            Request.Method.GET, URLs.TODOS, null, com.android.volley.Response.Listener { response ->
                for (i in 0 until 20) {
                    data.add(
                        ToDoData(
                            response.getJSONObject(i).getString("userId"),
                            response.getJSONObject(i).getString("id"),
                            response.getJSONObject(i).getString("title"),
                            response.getJSONObject(i).getBoolean("completed")
                        )
                    )
                }
                rvToDos.layoutManager = LinearLayoutManager(this)
                rvToDos.setHasFixedSize(true)
                rvToDos.adapter = ToDoAdapter(this, data)
            }, com.android.volley.Response.ErrorListener { error ->
                Log.e("mzn", error.message!!)
            })
        MySingleton.getInstance()!!.addToRequestQueue(jsonArrayRequest, "JsonArray")
    }
}

/*
private fun getToDos() {
    progressDialog.show()
    val service: ApiInterface =
        ApiClient.getRetrofitInstance()!!.create(ApiInterface::class.java)
    val call = service.getToDos()
    call.enqueue(object : Callback<ToDo> {
        override fun onFailure(call: Call<ToDo>, t: Throwable) {
            Log.e("mzn", t.message.toString())
            progressDialog.dismiss()
            Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_LONG).show()
        }

        override fun onResponse(call: Call<ToDo>, response:Response<ToDo>) {
            progressDialog.dismiss()
            if (response.isSuccessful) {
                val todos = response.body()
                Log.e("mzn", response.toString())
                initAdapter(todos!!)
            }
        }

    })

}


private fun initAdapter(todos: ToDo) {
    todoAdapter = ToDoAdapter(this@MainActivity, todos.list)
    rvToDos.layoutManager = LinearLayoutManager(this@MainActivity)
    rvToDos.setHasFixedSize(true)
    rvToDos.adapter = todoAdapter
}

 */
