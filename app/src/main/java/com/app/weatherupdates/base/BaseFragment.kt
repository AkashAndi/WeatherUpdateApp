package com.app.weatherupdates.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.FragmentActivity
import com.app.weatherupdates.R
import dagger.android.support.DaggerFragment

abstract class BaseFragment : DaggerFragment() {

    abstract val contentView: Int
        @LayoutRes get

    abstract fun viewModelSetup()

    abstract fun viewSetup()

    fun toolbarSetup(
            activity: FragmentActivity,
            toolbar: Toolbar,
            titleTextView: TextView? = null, @StringRes title: Int? = 0, @DrawableRes icon: Int? = 0,
            navigationIcon: Boolean? = true,
            titleString: String? = "",
            purposeString: Int? = null
    ) {
        val activityObj = activity as? AppCompatActivity
        activityObj?.setSupportActionBar(toolbar)
        activityObj?.supportActionBar?.apply {
            navigationIcon?.let { setDisplayHomeAsUpEnabled(it) }
            setDisplayShowTitleEnabled(false)
        }
        if (navigationIcon == true) {
            if (icon == 0) {
                toolbar.setNavigationIcon(R.drawable.ic_back_arrow_white)
            } else {
                icon?.let { toolbar.setNavigationIcon(it) }
            }
        }
        title?.takeIf { it != 0 }?.let {
            titleTextView?.text = getString(it)
        }
        titleString?.takeIf { it.isNotEmpty() }?.let {
            titleTextView?.text = it
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        contentView.takeIf { it != 0 }?.let {
            return inflater.inflate(contentView, container, false)
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModelSetup()
        viewSetup()
    }

    fun dismiss() {
        activity?.supportFragmentManager?.popBackStackImmediate()
    }

}