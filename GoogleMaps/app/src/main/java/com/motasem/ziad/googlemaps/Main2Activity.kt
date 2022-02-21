package com.motasem.ziad.googlemaps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.gms.location.LocationServices
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity() {
    val TAG = "mzn"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        btnGetLocation.setOnClickListener {
            getLastLocation()
        }
    }

    private fun getLastLocation() {
        val locationClient = LocationServices.getFusedLocationProviderClient(this)
        locationClient.lastLocation
            .addOnSuccessListener { location ->
                if (location != null) {
                    val lat = location.latitude
                    val long = location.longitude
                    val provider = location.provider

                    Log.e(TAG, "Latitude : $lat")
                    Log.e(TAG, "Longitude : $long")
                    Log.e(TAG, "Provider : $provider")
                }
            }
            .addOnFailureListener { exception ->
                Log.e(TAG, exception.message.toString())
            }
    }


}
