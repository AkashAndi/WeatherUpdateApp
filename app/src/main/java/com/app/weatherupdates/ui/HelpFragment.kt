package com.app.weatherupdates.ui

import android.view.MenuItem
import com.app.weatherupdates.R
import com.app.weatherupdates.base.BaseBindingFragment
import com.app.weatherupdates.databinding.FragmentHelpBinding
import kotlinx.android.synthetic.main.app_bar_default.*
import kotlinx.android.synthetic.main.fragment_help.*

class HelpFragment : BaseBindingFragment<FragmentHelpBinding>() {

    companion object {
        const val TAG = "HelpFragment"
        fun newInstance(): HelpFragment {
            return HelpFragment()
        }
    }

    override val contentView = R.layout.fragment_help

    override fun viewModelSetup() {

    }

    override fun viewSetup() {
        setHasOptionsMenu(true)
        toolbarSetup(requireActivity(), toolbarDefault, toolbarTitle, R.string.help, navigationIcon = true)
        val htmlDocument =
                "<html><body><h1>Weather App</h1><p><h2>Weather, everybody wants to know how it is going to be during the week. Will it be rainy, windy,\n" +
                        "or sunny? Luckily for us, in the information age, there are open APIs to retrieve information\n" +
                        "about it.\n...</h2></p>" +
                        "<h3><p>Landing Screen will show the bookmarked location</p>" +
                        "<p>You can delete it by long pressing the location and select delete from the dialog.</p>" +
                        "<p>You can See weather details by taping on bookmark location</p>" +
                        "<p>You can Change temperature unit from <b>Setting Screen<b/></p><h3></body></html>"

        webWiewHelp?.loadData(htmlDocument, "text/html; charset=utf-8", "UTF-8")

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