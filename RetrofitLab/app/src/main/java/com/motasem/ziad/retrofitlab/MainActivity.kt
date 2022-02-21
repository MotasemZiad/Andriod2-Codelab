package com.motasem.ziad.retrofitlab

import android.app.ProgressDialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var pd: ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnLogin.setOnClickListener {
            pd = ProgressDialog(this)
            pd.setMessage("Wait ,,, ")
            pd.setCancelable(false)

            login(edPhone.text.toString(), edPassword.text.toString())
        }
    }

    private fun login(phone: String, password: String) {
        pd.show()
        val service: ApiInterface =
            ApiClient.getRetrofitInstance()!!.create(ApiInterface::class.java)
        val call = service.login("secret-token", password, phone)
        call.enqueue(object : Callback<Login> {
            override fun onFailure(call: Call<Login>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<Login>, response: Response<Login>) {
                pd.dismiss()
                val login: Login? = response.body()
                Toast.makeText(this@MainActivity, login.toString(), Toast.LENGTH_LONG).show()
            }

        })
    }

}
