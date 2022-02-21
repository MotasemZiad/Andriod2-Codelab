package com.motasem.ziad.webservice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val obj = JSONObject()
        obj.put("id", 1)
        obj.put("name", "Motasem Z. AbuNema")
        obj.put("salary", 1500)
        obj.put("email", "mnima2000@iugaza.edu.ps")

        val courses = JSONArray()
        courses.put("ANDROID")
        courses.put("KOTLIN")
        courses.put("JAVA")

        obj.put("courses", courses)

        val phone = JSONObject()
        phone.put("mobile", "0599838964")
        phone.put("office", "082811040")

        obj.put("phone", phone)

        Log.e("mzn", obj.toString())




        btnGet.setOnClickListener {
            val id = obj.getInt("id")
            val name = obj.getString("name")
            val salary = obj.getDouble("salary")
            val email = obj.getString("email")
            var s = ""
            val courses = obj.getJSONArray("courses")
            for (i in 0 until obj.length()) {
                s += "${courses[0]}, "
            }
            val phone = obj.getJSONObject("phone")
            val mobile = phone.getString("mobile")
            val office = phone.getString("office")

            val result =
                "ID: $id\nName: $name\nSalary: $salary\nEmail: $email\nCourses: $courses\n Phone: $phone"

            tvMsg.text = result
        }
    }
}
