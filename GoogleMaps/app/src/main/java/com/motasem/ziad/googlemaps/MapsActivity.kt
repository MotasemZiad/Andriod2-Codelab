package com.motasem.ziad.googlemaps

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        /* val gaza = LatLng(31.512990, 34.445366) // Latitude, Longitude
         mMap.addMarker(MarkerOptions().position(gaza).title("Marker in Gaza Sky Geeks"))
         //mMap.moveCamera(CameraUpdateFactory.newLatLng(gaza))
         //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(gaza, 20f))
         mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(gaza, 14f)) */

        //31.514638, 34.439221

        /* val iug = LatLng(31.514407, 34.439316)
           val markerOptions = MarkerOptions()
           markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.notepad_small))
           mMap.addMarker(
           markerOptions.position(iug).title("IUG").snippet("Islamic University of Gaza"))
           mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(iug, 12f))
        */

        mMap.mapType = GoogleMap.MAP_TYPE_TERRAIN

        mMap.uiSettings.isCompassEnabled = true
        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.uiSettings.isMapToolbarEnabled = false

        val iug = LatLng(31.514407, 34.439316)
        val markerOptions = MarkerOptions()
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
        mMap.addMarker(
            markerOptions.position(iug).title("IUG").snippet("Islamic University of Gaza")
        )
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(iug, 12f))

        val iug2 = LatLng(31.044407, 34.739316)
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA))
        mMap.addMarker(
            markerOptions.position(iug2).title("IUG2").snippet("Islamic University of Gaza2")
        )
        //mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(iug, 12f))

        mMap.addPolyline(
            PolylineOptions()
                .add(LatLng(31.514407, 34.439316))
                .add(LatLng(31.644407, 34.739316))
                .color(Color.DKGRAY)
                .visible(true)
        )

        val polygon = mMap.addPolygon(
            PolygonOptions()
                .add(
                    LatLng(30.514407, 34.939316),
                    LatLng(32.514407, 37.439316),
                    LatLng(35.514407, 34.739316),
                    LatLng(31.314407, 34.039316)
                )
                .clickable(true)
                .fillColor(Color.CYAN)
                .strokeColor(Color.RED)
                .strokeWidth(5f)
        )
        polygon.tag = "alpha"

        val mCircle = mMap.addCircle(
            CircleOptions()
                .center(LatLng(37.897113, 32.485671))
                .radius(1550.0)
                .clickable(true)
                .fillColor(Color.YELLOW)
                .strokeColor(Color.BLACK)
                .strokeWidth(8f)
        )
        mCircle.tag = "beta"

        mMap.setOnCircleClickListener { circle ->
            if (circle.tag!! == "beta")
                Toast.makeText(this, "beta Clicked", Toast.LENGTH_SHORT).show()
        }


    }
}
