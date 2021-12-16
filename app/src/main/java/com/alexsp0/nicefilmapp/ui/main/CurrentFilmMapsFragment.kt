package com.alexsp0.nicefilmapp.ui.main

import android.Manifest
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.LocationManager
import android.net.Uri
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.alexsp0.nicefilmapp.MainActivity
import com.alexsp0.nicefilmapp.R
import com.alexsp0.nicefilmapp.REQUEST_CODE

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class CurrentFilmMapsFragment(country : String) : Fragment() {

    private val callback = OnMapReadyCallback { googleMap ->
        val act = activity as MainActivity
        val geoCoder = Geocoder(act.applicationContext)
        val address = geoCoder.getFromLocationName(country,1)
        if (address != null) {
            val location = address[0]
            val pos = LatLng(location.latitude, location.longitude)
            googleMap.addMarker(MarkerOptions().position(pos).title("Production country is here!"))
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(pos))
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_current_film_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkNecessaryPermissions()
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }
    private fun checkNecessaryPermissions() {
        val act = activity as MainActivity
        if(ContextCompat.checkSelfPermission(act,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED) {
            getGeo()
        } else {

            AlertDialog.Builder(act).setTitle("Разрешение на геолокацию")
                .setMessage("Очень надо геолокацию, смело разрешайте, не утащим")
                .setPositiveButton("Разрешаю!") { _, _ -> requestNecessaryPermission()}
                .setNegativeButton("Я скрываюсь!") { dialog, _ -> dialog.dismiss() }
                .setNeutralButton("Идем в разрашения?") { dialog: DialogInterface, which ->
                    val intent = Intent()
                    intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                    intent.setData(Uri.parse("package:" + act.packageName))
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
                    intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS)
                    act.startActivity(intent)

                }
                .create().show()
            requestNecessaryPermission()
        }
    }

    private fun requestNecessaryPermission() {
        val act = activity as MainActivity
        ActivityCompat.requestPermissions(act,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_CODE
        )
    }
    private fun getGeo() {

    }
}