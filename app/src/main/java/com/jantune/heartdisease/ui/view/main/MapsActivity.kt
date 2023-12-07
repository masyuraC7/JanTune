package com.jantune.heartdisease.ui.view.main

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.jantune.heartdisease.databinding.ActivityMapsBinding
import com.jantune.heartdisease.R
import com.jantune.heartdisease.ui.view.main.identification.detail.IdentificationDetailActivity
import org.json.JSONObject

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBar)

        supportActionBar?.title = "Lokasi Pusat Kesehatan"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val toolbar = binding.appBar

        toolbar.overflowIcon?.setTint(ContextCompat.getColor(this, android.R.color.white))
        toolbar.setNavigationIcon(R.drawable.ic_back) // Warna putih
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        binding.buttonSearchRS.setOnClickListener {
            findNearbyHospitals()
            getMyLocation()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.map_options, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                startActivity(Intent(this@MapsActivity, IdentificationDetailActivity::class.java))
                finish()
                true
            }

            R.id.normal_type -> {
                mMap.mapType = GoogleMap.MAP_TYPE_NORMAL
                true
            }

            R.id.satellite_type -> {
                mMap.mapType = GoogleMap.MAP_TYPE_SATELLITE
                true
            }

            R.id.terrain_type -> {
                mMap.mapType = GoogleMap.MAP_TYPE_TERRAIN
                true
            }

            R.id.hybrid_type -> {
                mMap.mapType = GoogleMap.MAP_TYPE_HYBRID
                true
            }

            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        getMyLocation()
        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.uiSettings.isIndoorLevelPickerEnabled = true
        mMap.uiSettings.isCompassEnabled = true
        mMap.uiSettings.isMapToolbarEnabled = true
    }

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                getMyLocation()
            }
        }

    private fun getMyLocation() {
        if (ContextCompat.checkSelfPermission(
                this.applicationContext,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            mMap.isMyLocationEnabled = true
        } else {
            requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    private fun findNearbyHospitals() {
        // Use FusedLocationProviderClient to get the last known location
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Request location permissions if not granted
            requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            return
        }

        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                location?.let {
                    // Call the API with the current location
                    val currentLat = location.latitude
                    val currentLng = location.longitude
                    val placeType = "hospital"
                    Log.d("location currentLat", currentLat.toString())
                    Log.d("location currentLng", currentLng.toString())
                    val googleMapKey = "AIzaSyDng476qiOpjwOxqQxsOSk2SUki5dZklHU"

                    val url =
                        "https://maps.googleapis.com/maps/api/place/nearbysearch/json" +
                                "?location=$currentLat,$currentLng" +
                                "&radius=5000" +
                                "&type=$placeType" +
                                "&key=$googleMapKey"

                    val requestQueue = Volley.newRequestQueue(this)
                    val jsonObjectRequest = JsonObjectRequest(
                        Request.Method.GET, url, null,
                        { response ->
                            handleNearbyHospitalsResponse(response)
                        },
                        { error ->
                            error.printStackTrace()
                        })

                    requestQueue.add(jsonObjectRequest)
                    // Animasi kamera ke lokasi Anda
                    val myLocation = LatLng(currentLat, currentLng)
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myLocation, 15f))
                }
            }
            .addOnFailureListener { exception ->
                // Handle failure
                exception.printStackTrace()
            }
    }

    private fun handleNearbyHospitalsResponse(response: JSONObject) {
        Log.d("API_RESPONSE", response.toString())
        // Clear existing markers on the map
        mMap.clear()

        // Parse the response and add markers to the map
        val results = response.getJSONArray("results")
        for (i in 0 until results.length()) {
            val place = results.getJSONObject(i)
            val location = place.getJSONObject("geometry").getJSONObject("location")
            val lat = location.getDouble("lat")
            val lng = location.getDouble("lng")
            val name = place.getString("name")

            // Add marker to the map
            val markerOptions = MarkerOptions().position(LatLng(lat, lng)).title(name)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
            mMap.addMarker(markerOptions)
        }

        // Move camera to the first hospital location
        if (results.length() > 0) {
            val firstHospital = results.getJSONObject(0)
            val location = firstHospital.getJSONObject("geometry").getJSONObject("location")
            val lat = location.getDouble("lat")
            val lng = location.getDouble("lng")
            val latLng = LatLng(lat, lng)
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12f))
        }
    }

}
