package com.app.weatherupdates.ui

import android.annotation.SuppressLint
import android.content.Intent
import com.app.weatherupdates.R
import com.app.weatherupdates.base.BaseActivity
import com.app.weatherupdates.ui.BookMarkedLocationFragment.Companion.EXTRAS_SELECTED_LATITUDE
import com.app.weatherupdates.ui.BookMarkedLocationFragment.Companion.EXTRAS_SELECTED_LONGITUDE
import com.app.weatherupdates.ui.BookMarkedLocationFragment.Companion.REQUEST_PLACE_PICKER
import com.app.weatherupdates.utils.showSnack
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_pick_location.*

class PickLocationActivity : BaseActivity() {

    private var markerPosition: Marker? = null

    private var selectedLatLong: LatLng? = null

    override val contentView = R.layout.activity_pick_location

    private val position = LatLng(23.0225, 72.5714)

    override fun viewModelSetup() {

    }

    override fun viewSetup() {
        with(mapView) {
            // Initialise the MapView
            onCreate(null)
            // Set the map ready callback to receive the GoogleMap object
            getMapAsync {
                MapsInitializer.initialize(applicationContext)
                setMapLocation(it)
            }
        }

        btnSelectLocation?.setOnClickListener {
            if (selectedLatLong == null) {
                btnSelectLocation?.showSnack("Kindly wait for Map get loaded")
                return@setOnClickListener
            }
            val intent = Intent()
            intent.putExtra(EXTRAS_SELECTED_LATITUDE, selectedLatLong?.latitude)
            intent.putExtra(EXTRAS_SELECTED_LONGITUDE, selectedLatLong?.longitude)
            setResult(REQUEST_PLACE_PICKER, intent)
            finish()
        }
    }

    @SuppressLint("MissingPermission")
    private fun setMapLocation(map: GoogleMap) {
        with(map) {
            moveCamera(CameraUpdateFactory.newLatLngZoom(position, 11f))
            isMyLocationEnabled = true
            addMarker(MarkerOptions().position(position))
            mapType = GoogleMap.MAP_TYPE_NORMAL
            setOnMapClickListener {
                selectedLatLong = it
                if (markerPosition != null) {
                    markerPosition?.remove()
                }
                markerPosition = this.addMarker(MarkerOptions().position(it))
            }
        }
    }

    override val style = R.style.AppTheme
}