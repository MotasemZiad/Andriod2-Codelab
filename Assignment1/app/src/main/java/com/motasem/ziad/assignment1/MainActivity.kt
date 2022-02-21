package com.motasem.ziad.assignment1

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private val SPLASH_TIME = 2500L
    private lateinit var myHandler: Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myHandler = Handler()
        myHandler.postDelayed({ goToLoginActivity() }, SPLASH_TIME)

    }

    private fun goToLoginActivity() {
        val intent = Intent(this, SignInAct::class.java)
        startActivity(intent)
        finish()
    }
}
