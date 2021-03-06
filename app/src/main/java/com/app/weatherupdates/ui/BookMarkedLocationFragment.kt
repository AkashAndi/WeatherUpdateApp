package com.app.weatherupdates.ui

import android.Manifest
import android.app.Activity.RESULT_OK
import android.app.AlertDialog
import android.content.DialogInterface.BUTTON_NEGATIVE
import android.content.DialogInterface.BUTTON_POSITIVE
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.os.Build
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.app.weatherupdates.R
import com.app.weatherupdates.base.BaseBindingFragment
import com.app.weatherupdates.databinding.FragmentBookmarkedLocationBinding
import com.app.weatherupdates.misc.persistance.entity.BookMarkedLocation
import com.app.weatherupdates.ui.adapter.BookmarkAdapter
import com.app.weatherupdates.utils.*
import com.app.weatherupdates.viewmodel.WeatherViewModel
import kotlinx.android.synthetic.main.app_bar_default.*
import kotlinx.android.synthetic.main.fragment_bookmarked_location.*
import java.io.IOException
import java.util.*
import javax.inject.Inject


class BookMarkedLocationFragment : BaseBindingFragment<FragmentBookmarkedLocationBinding>() {

    companion object {
        const val TAG = "BookMarkedLocationFragment"
        const val REQUEST_PLACE_PICKER = 1
        const val EXTRAS_SELECTED_LATITUDE = "EXTRAS_SELECTED_LATITUDE"
        const val EXTRAS_SELECTED_LONGITUDE = "EXTRAS_SELECTED_LONGITUDE"
        fun newInstance(): BookMarkedLocationFragment {
            return BookMarkedLocationFragment()
        }
    }

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private val viewModel by lazy {
        activityViewModelProvider<WeatherViewModel>(factory)
    }


    private val bookmarkAdapter by lazy {
        BookmarkAdapter(mutableListOf(), {
            navigateToDetailsFragment(
                    viewModel.bookmarkLiveData.value?.get(it)
                            ?: BookMarkedLocation()
            )
        }) {
            viewModel.bookmarkLiveData.value?.get(it)?.timeStamp?.let { timeStamp ->
                alerBeforeDelete(timeStamp)
            }
        }
    }

    private fun alerBeforeDelete(timeStamp: String) {
        val alertDialog = AlertDialog.Builder(requireContext(), 0).create()
        alertDialog.setButton(
                BUTTON_POSITIVE, getString(R.string.delete)
        ) { dialog, _ ->
            viewModel.deleteLocation(timeStamp)
            dialog.dismiss()
        }
        alertDialog.setButton(BUTTON_NEGATIVE, getString(R.string.cancel)) { dialog, _ ->
            dialog.dismiss()
        }
        alertDialog.setTitle(getString(R.string.app_name))
        alertDialog.setMessage(getString(R.string.are_you_sure))
        alertDialog.show()
    }

    private fun navigateToDetailsFragment(bookMarkedLocation: BookMarkedLocation) {
        (requireActivity() as WeatherActivity?)?.replaceFragmentSafely(
                DetailsFragment.newInstance(
                        bookMarkedLocation.address
                                ?: "", bookMarkedLocation.latitude ?: 0.0,
                        bookMarkedLocation.longitude ?: 0.0
                ),
                DetailsFragment.TAG, addToBackStack = true, containerViewId = R.id.flContainer
        )
    }

    override val contentView = R.layout.fragment_bookmarked_location

    override fun viewModelSetup() {
        initObserver()
    }

    private fun initObserver() {
        baseViewModel = viewModel
        viewModel.bookmarkLiveData.observe(this, {
            bookmarkAdapter.setData(it)
        })
    }

    override fun viewSetup() {
        (requireActivity() as WeatherActivity?)?.snackView = rvBookmarks
        setHasOptionsMenu(true)
        toolbarSetup(
                requireActivity(),
                toolbarDefault,
                toolbarTitle,
                R.string.bookmarks,
                navigationIcon = false
        )
        binding?.viewModel = viewModel
        initListener()
        rvBookmarks?.adapter = bookmarkAdapter
        viewModel.getBookmark()
    }

    private fun initListener() {
        fabAddLocation?.setOnClickListener {
            fabAddLocation?.avoidDoubleClicks()
            if (ContextCompat.checkSelfPermission(
                            requireContext(),
                            Manifest.permission.ACCESS_FINE_LOCATION
                    ) == PackageManager.PERMISSION_GRANTED
            ) {
                navigateToPickLocation()
            } else {
                getLocationPermission()
            }
        }

    }

    private fun navigateToPickLocation() {
        val intent = Intent(requireActivity(), PickLocationActivity::class.java)
        startActivityForResult(intent, REQUEST_PLACE_PICKER)
    }

    private fun getLocationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // this mean device os is greater or equal to Marshmallow.
            if (ActivityCompat.checkSelfPermission(
                            requireContext(),
                            Manifest.permission.ACCESS_FINE_LOCATION
                    )
                    != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                            requireContext(),
                            Manifest.permission.ACCESS_COARSE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
            ) {
                // here we are going to request location run time permission.
                ActivityCompat.requestPermissions(
                        requireActivity(),
                        arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                        WeatherActivity.REQUEST_PERMISSIONS_REQUEST_CODE
                )
                return
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_PLACE_PICKER) {
                val latitude = data?.getDoubleExtra(EXTRAS_SELECTED_LATITUDE, 0.0) ?: 0.0
                val longitude = data?.getDoubleExtra(EXTRAS_SELECTED_LONGITUDE, 0.0) ?: 0.0
                val bookMarkData =
                        mapOf(Constants.INSERT_BOOKMARK_DB to getAddress(latitude, longitude))
                viewModel.insertIntoDB(bookMarkData)
            }
        }
    }

    override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == WeatherActivity.REQUEST_PERMISSIONS_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                navigateToPickLocation()
            } else {
                showAlertDialog(
                        getString(R.string.app_name), "Location Permission is Required",
                        positiveText = "Request Again",
                        accepted = { dialogInterface, _ ->
                            {
                                dialogInterface.dismiss()
                            }
                        }
                )
            }
        }

    }

    private fun getAddress(lat: Double, lng: Double): BookMarkedLocation {
        val geocoder = Geocoder(requireContext(), Locale.getDefault())
        return try {
            val addresses: List<Address> = geocoder.getFromLocation(lat, lng, 1)
            val obj: Address = addresses[0]
            val add: String = obj.getAddressLine(0)
            BookMarkedLocation(lat, lng, add.trim(), Calendar.getInstance().timeInMillis.toString())
        } catch (e: IOException) {
            e.printStackTrace()
            BookMarkedLocation()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.options_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_options -> {
                (requireActivity() as WeatherActivity?)?.replaceFragmentSafely(
                        HelpFragment.newInstance(),
                        HelpFragment.TAG, addToBackStack = true, containerViewId = R.id.flContainer
                )
            }
            R.id.menu_settings -> {
                (requireActivity() as WeatherActivity?)?.replaceFragmentSafely(
                        SettingsFragment.newInstance(),
                        SettingsFragment.TAG, addToBackStack = true, containerViewId = R.id.flContainer
                )
            }
            else -> return super.onOptionsItemSelected(item)
        }
        return super.onOptionsItemSelected(item)
    }

}