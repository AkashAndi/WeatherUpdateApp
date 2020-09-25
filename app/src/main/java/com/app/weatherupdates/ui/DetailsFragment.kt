package com.app.weatherupdates.ui

import android.os.Bundle
import com.app.weatherupdates.R
import com.app.weatherupdates.base.BaseBindingFragment
import com.app.weatherupdates.databinding.FragmentDetailsBinding

class DetailsFragment : BaseBindingFragment<FragmentDetailsBinding>() {

    companion object {
        const val EXTRAS_ADDRES = "EXTRAS_ADDRES"
        const val TAG = "DetailsFragment"
        fun newInstance(address: String): DetailsFragment {
            val detailsFragment = DetailsFragment()
            val bundle = Bundle()
            bundle.putString(EXTRAS_ADDRES, address)
            detailsFragment.arguments = bundle
            return detailsFragment
        }
    }

    override val contentView = R.layout.fragment_details

    override fun viewModelSetup() {

    }

    override fun viewSetup() {

    }
}