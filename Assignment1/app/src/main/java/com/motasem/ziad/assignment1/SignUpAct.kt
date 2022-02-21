package com.motasem.ziad.assignment1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.motasem.ziad.assignment1.db.DatabaseHelper
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.activity_sign_up.edUsername

class SignUpAct : AppCompatActivity() {
    private lateinit var db: DatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        db = DatabaseHelper(this)
        btnSignUp.setOnClickListener {
            if (edUsername.text.isEmpty())
                edUsername.error = "Fill this field"
            if (edPassword.text.isEmpty())
                edPassword.error = "Fill this field"
            if (edEmail.text.isEmpty())
                edEmail.error = "Fill this field"
            if (edPhoneNumber.text.isEmpty())
                edPhoneNumber.error = "Fill this field"

            /*   if (!(edEmail.text.contains("@") && edEmail.text.contains(".")))
                   Toast.makeText(this, "Email must contain @ and . signs", Toast.LENGTH_SHORT).show()

               if (edPassword.text.length < 8)
                   Toast.makeText(
                       this,
                       "Password must contain 8 or more character",
                       Toast.LENGTH_SHORT
                   ).show()

               if (edPhoneNumber.text.length > 10)
                   Toast.makeText(
                       this,
                       "Phone number must not contain more than 10 numbers",
                       Toast.LENGTH_SHORT
                   ).show() */

            if (edUsername.text.isNotEmpty() && edPassword.text.isNotEmpty() && edEmail.text.isNotEmpty() && edPhoneNumber.text.isNotEmpty()) {
                if (db.insertUser(
                        edUsername.text.toString(),
                        edEmail.text.toString(),
                        edPassword.text.toString(),
                        edPhoneNumber.text.toString()
                    )
                ) {
                    Toast.makeText(this, "Created Account Successfully", Toast.LENGTH_SHORT).show()
                    val i = Intent(this, SignInAct::class.java)
                    startActivity(i)
                } else {
                    Toast.makeText(this, "Error!!", Toast.LENGTH_SHORT).show()
                }
            }


        }

        tvSignIn.setOnClickListener {
            val i = Intent(this, SignInAct::class.java)
            startActivity(i)
        }

    }
}
