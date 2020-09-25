package com.app.weatherupdates.ui

import androidx.lifecycle.ViewModelProvider
import com.app.weatherupdates.R
import com.app.weatherupdates.base.BaseActivity
import com.app.weatherupdates.utils.replaceFragmentSafely
import com.app.weatherupdates.utils.viewModelProvider
import com.app.weatherupdates.viewmodel.WeatherViewModel
import javax.inject.Inject

class WeatherActivity : BaseActivity() {

    companion object {
        const val REQUEST_PERMISSIONS_REQUEST_CODE = 555
    }

    override val contentView = R.layout.activity_main

    override val style = R.style.AppTheme

    override fun viewModelSetup() {

    }

    override fun viewSetup() {
        navigateToBookMarkFragment()
    }

    private fun navigateToBookMarkFragment() {
        replaceFragmentSafely(
                BookMarkedLocationFragment.newInstance(), BookMarkedLocationFragment.TAG,
                addToBackStack = false, containerViewId = R.id.flContainer
        )
    }

    override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        supportFragmentManager.findFragmentById(R.id.flContainer)?.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }


}