package com.mobileapps.isschallengekotlin.ui

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.mobileapps.isschallengekotlin.R
import com.mobileapps.isschallengekotlin.adapters.IssAdapter
import com.mobileapps.isschallengekotlin.models.IssResponse
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), MainViewInterface  {

    val TAG = "MainActivity"
    lateinit var adapter: IssAdapter
    lateinit var mainPresenter : MainPresenter
    lateinit var fusedLocationClient: FusedLocationProviderClient
    lateinit var lat : String
    lateinit var long: String




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)


       startPermission()


    }

    private fun startPermission() {
        if (checkPermission(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION)) {
            fusedLocationClient.lastLocation?.
                addOnSuccessListener(this
                ) { location ->
                    // Got last known location. In some rare
                    // situations this can be null.
                    if(location == null) {
                        // TODO, handle it
                    } else location.apply {
                        lat = location.latitude.toString()
                        long = location.longitude.toString()
                        setupMVP()
                        setupViews()
                        getResponse()
                    }
                }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray)
    {
        when (requestCode) {
            PERMISSION_ID -> {
                startPermission()
            }
        }
    }

    private fun getResponse()
    {
        mainPresenter.getResponse()
    }

    private fun setupViews()
    {
        rvIssResponses.layoutManager = LinearLayoutManager(this)
    }

    private fun setupMVP()
    {//"33.90","-84.47"
        mainPresenter = MainPresenter(this,lat,long)
    }


    override fun displayResponse(issResponse: IssResponse?)
    {
        if (issResponse != null) {
            adapter = IssAdapter(issResponse.response)
            rvIssResponses!!.adapter = adapter
        } else {
            Log.d(TAG, "Weather response null")
        }
    }

    override fun displayError(s: String) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show()
    }

    val PERMISSION_ID = 42
    private fun checkPermission(vararg perm:String) : Boolean {
        val havePermissions = perm.toList().all {
            ContextCompat.checkSelfPermission(this,it) ==
                    PackageManager.PERMISSION_GRANTED
        }
        if (!havePermissions) {
            if(perm.toList().any {
                    ActivityCompat.
                        shouldShowRequestPermissionRationale(this, it)}
            ) {
                val dialog = AlertDialog.Builder(this)
                    .setTitle("Permission")
                    .setMessage("Permission needed!")
                    .setPositiveButton("OK") { id, v ->
                        ActivityCompat.requestPermissions(
                            this, perm, PERMISSION_ID)
                    }
                    .setNegativeButton("No", {id, v -> })
                    .create()
                dialog.show()
            } else {
                ActivityCompat.requestPermissions(this, perm, PERMISSION_ID)
            }
            return false
        }
        return true
    }

}
