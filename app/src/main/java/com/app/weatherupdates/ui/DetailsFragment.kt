package com.app.weatherupdates.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import com.app.weatherupdates.R
import com.app.weatherupdates.base.BaseBindingFragment
import com.app.weatherupdates.databinding.FragmentDetailsBinding
import com.app.weatherupdates.utils.viewModelProvider
import com.app.weatherupdates.viewmodel.WeatherDetailsViewModel
import kotlinx.android.synthetic.main.app_bar_default.*
import kotlinx.android.synthetic.main.fragment_details.*
import javax.inject.Inject

class DetailsFragment : BaseBindingFragment<FragmentDetailsBinding>() {

    companion object {
        const val EXTRAS_ADDRES = "EXTRAS_ADDRES"
        const val EXTRAS_LAT = "EXTRAS_LAT"
        const val EXTRAS_LONG = "EXTRAS_LONG"
        const val TAG = "DetailsFragment"
        fun newInstance(address: String, lat: Double, long: Double): DetailsFragment {
            val detailsFragment = DetailsFragment()
            val bundle = Bundle()
            bundle.putString(EXTRAS_ADDRES, address)
            bundle.putDouble(EXTRAS_LAT, lat)
            bundle.putDouble(EXTRAS_LONG, long)
            detailsFragment.arguments = bundle
            return detailsFragment
        }
    }

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private val viewModel by lazy {
        viewModelProvider<WeatherDetailsViewModel>(factory)
    }


    override val contentView = R.layout.fragment_details

    override fun viewModelSetup() {
        baseViewModel = viewModel
    }

    override fun viewSetup() {
        setHasOptionsMenu(true)
        toolbarSetup(requireActivity(), toolbarDefault, toolbarTitle, R.string.details, navigationIcon = true)
        (requireActivity() as WeatherActivity?)?.snackView = rootCL
        binding?.viewModel = viewModel
        viewModel.address = arguments?.getString(EXTRAS_ADDRES)
        viewModel.latitude = arguments?.getDouble(EXTRAS_LAT) ?: 0.0
        viewModel.longitude = arguments?.getDouble(EXTRAS_LONG) ?: 0.0
        viewModel.getWeatherDetails()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                requireActivity().onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}