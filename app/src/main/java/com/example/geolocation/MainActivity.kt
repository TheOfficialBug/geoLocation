package com.example.geolocation

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    lateinit var btnCamera: Button
    lateinit var btnLocation : Button

    private val CAMERA_PERMISSION_CODE = 125
    private val LOCATION_PERMISSION_CODE = 110

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnCamera = findViewById(R.id.camera_btn)
        btnLocation = findViewById(R.id.location_btn)

        btnCamera.setOnClickListener {
            checkPermission(Manifest.permission.CAMERA, CAMERA_PERMISSION_CODE)
        }

        btnLocation.setOnClickListener {
            checkPermission(Manifest.permission.ACCESS_FINE_LOCATION, LOCATION_PERMISSION_CODE)
        }

    }

    private fun checkPermission(permission:String, requestCode:Int) {
        if (ContextCompat.checkSelfPermission(this@MainActivity, permission) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this@MainActivity, arrayOf(permission), requestCode)
        }else {
            Toast.makeText(this@MainActivity, "Permission Granted already", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == CAMERA_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                btnCamera.setText("Permission Granted")
                Toast.makeText(this@MainActivity, "Camera permission granted", Toast.LENGTH_SHORT).show()
            }else {
                Toast.makeText(this@MainActivity, "Camera permission denied", Toast.LENGTH_SHORT).show()
            }

        } else if (requestCode == LOCATION_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                btnLocation.setText("Permission Granted")
                Toast.makeText(this@MainActivity, "Location permission granted", Toast.LENGTH_SHORT).show()
            }else {
                Toast.makeText(this@MainActivity, "Location permission denied", Toast.LENGTH_SHORT).show()
            }

        }

    }

}





