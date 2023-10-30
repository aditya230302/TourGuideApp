package com.example.tourguideapp




import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.Locale

class Profile : AppCompatActivity() {

    private lateinit var btnInsertData:Button
    private lateinit var btnFetchData:Button
    private lateinit var map:ImageView

    private val LOCATION_PERMISSION_RED_CODE=1000
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var latitude:Double=0.0
    private var longitude:Double=0.0

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        lateinit  var animFadeIn: Animation

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        btnInsertData=findViewById(R.id.btnInsertData)
        btnFetchData=findViewById(R.id.btnFetchData)
        map = findViewById(R.id.map)

        animFadeIn = AnimationUtils.loadAnimation(
            applicationContext,R.anim.fade_in
        )

        map.visibility = View.VISIBLE
        map.startAnimation(animFadeIn)

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)
        bottomNav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home -> {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)

                    Toast.makeText(this, "Home accessed", Toast.LENGTH_LONG).show()
                    true
                }

                else -> false
            }
        }


        btnInsertData.setOnClickListener {
            val intent=Intent(this,InsertionActivity::class.java)
            startActivity(intent)
        }

        btnFetchData.setOnClickListener {
            val intent=Intent(this,FetchingActivity::class.java)
            startActivity(intent)
        }

        map.setOnClickListener{
            var mp = MediaPlayer.create(this,R.raw.map)
            mp.start()
            openMap()
        }

    }


    @SuppressLint("MissingSuperCall")
    override  fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {

        when(requestCode){
            LOCATION_PERMISSION_RED_CODE->{
                if(grantResults.isNotEmpty() && grantResults[0]== PackageManager.PERMISSION_GRANTED){

                }
                else{
                    Toast.makeText(this,"Grant permission to location",Toast.LENGTH_SHORT)
                }
            }
        }

    }

    private fun openMap(){
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_RED_CODE
            )
            return

        }
        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            Geocoder(this, Locale.getDefault())

        }.addOnFailureListener {
            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
        }
        val uri = Uri.parse("geo:$latitude,$longitude")
        val map = Intent(Intent.ACTION_VIEW, uri)
        map.setPackage("com.google.android.apps.maps")
        startActivity(map)
    }
}