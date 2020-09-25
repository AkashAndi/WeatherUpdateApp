package com.app.weatherupdates.ui

import android.app.AlertDialog
import android.content.DialogInterface
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import com.app.weatherupdates.R
import com.app.weatherupdates.base.BaseBindingFragment
import com.app.weatherupdates.databinding.FragmentSettingsBinding
import com.app.weatherupdates.utils.avoidDoubleClicks
import com.app.weatherupdates.utils.gone
import com.app.weatherupdates.utils.showSnack
import com.app.weatherupdates.utils.viewModelProvider
import com.app.weatherupdates.viewmodel.WeatherDetailsViewModel
import kotlinx.android.synthetic.main.app_bar_default.*
import kotlinx.android.synthetic.main.fragment_settings.*
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
        baseViewModel = viewModel
        initObservers()
    }

    private fun initObservers() {
        viewModel.deleteALlLiveData.observe(this, {
            if (it) {
                btnDeleteAll?.showSnack(getString(R.string.all_bookmark_deleted))
                viewModel.setDeleteAllLiveDataFalse()
                btnDeleteAll?.gone()
            }
        })
    }

    override fun viewSetup() {
        binding?.viewModel = viewModel
        setHasOptionsMenu(true)
        toolbarSetup(requireActivity(), toolbarDefault, toolbarTitle, R.string.details, navigationIcon = true)
        (requireActivity() as WeatherActivity?)?.snackView = rgUnit
        initListener()
    }

    private fun initListener() {
        btnDeleteAll?.setOnClickListener {
            btnDeleteAll?.avoidDoubleClicks()
            alerBeforeDeleteAll()
        }
    }

    private fun alerBeforeDeleteAll() {
        val alertDialog = AlertDialog.Builder(requireContext(), 0).create()
        alertDialog.setButton(
                DialogInterface.BUTTON_POSITIVE, getString(R.string.delete_all)
        ) { dialog, _ ->
            viewModel.deleteAll()
            dialog.dismiss()
        }
        alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, getString(R.string.cancel)) { dialog, _ ->
            dialog.dismiss()
        }
        alertDialog.setTitle(getString(R.string.app_name))
        alertDialog.setMessage(getString(R.string.are_you_sure_delete_all))
        alertDialog.show()
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