package com.motasem.ziad.volley

import android.content.Intent
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.appcompat.app.AppCompatActivity
import coil.api.load
import com.android.volley.toolbox.ImageLoader
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main3.*

class Main3Activity : AppCompatActivity() {
    val IMAGE_URL = "https://photoartinc.com/wp-content/uploads/2018/02/great-photos-4.jpg"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        btnGetImage.setOnClickListener {
            // NetworkImageView by Volley
            val imageLoader: ImageLoader = MySingleton.getInstance()!!.getImageLoader()!!
            imgVolley.setImageUrl(IMAGE_URL, imageLoader)
            // Picasso by Square
            Picasso.get().load(IMAGE_URL).into(imgPicasso)
            // Glide by Bump Technologies
            Glide.with(this).load(IMAGE_URL).into(imgGlide)
            // Fresco by Facebook
            imgFresco.setImageURI(IMAGE_URL)
            // Kotlin Coroutines by Coil
            imgCoil.load(IMAGE_URL)
        }

        btnGoToMain4.setOnClickListener {
            startActivity(Intent(this, Main4Activity::class.java))
        }

    }
}
