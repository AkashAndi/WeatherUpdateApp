package com.app.weatherupdates.ui

import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import com.app.weatherupdates.R
import com.app.weatherupdates.base.BaseBindingFragment
import com.app.weatherupdates.databinding.FragmentSettingsBinding
import com.app.weatherupdates.utils.viewModelProvider
import com.app.weatherupdates.viewmodel.WeatherDetailsViewModel
import kotlinx.android.synthetic.main.app_bar_default.*
import javax.inject.Inject

class SettingsFragment : BaseBindingFragment<FragmentSettingsBinding>() {

    companion object {
        const val TAG = "SettingsFragment"
        fun newInstance(): SettingsFragment {
            return SettingsFragment()
        }
    }

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private val viewModel by lazy {
        viewModelProvider<WeatherDetailsViewModel>(factory)
    }

    override val contentView = R.layout.fragment_settings

    override fun viewModelSetup() {
    }

    override fun viewSetup() {
        binding?.viewModel = viewModel
        setHasOptionsMenu(true)
        toolbarSetup(requireActivity(), toolbarDefault, toolbarTitle, R.string.details, navigationIcon = true)
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