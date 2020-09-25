package com.app.weatherupdates.base

import android.os.Bundle
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.annotation.StyleRes
import androidx.lifecycle.Observer
import com.app.weatherupdates.utils.hideKeyboard
import com.app.weatherupdates.utils.showSnack
import dagger.android.support.DaggerAppCompatActivity


abstract class BaseActivity : DaggerAppCompatActivity() {

    companion object {
        const val TAG = "BASE_ACTIVITY"
    }

    var baseViewModel: BaseObservableViewModel? = null

    var snackView: ViewGroup? = null

    var savedInstanceState: Bundle? = null

    abstract val contentView: Int
        @LayoutRes get

    abstract fun viewModelSetup()

    abstract fun viewSetup()

    abstract val style: Int
        @StyleRes get

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.savedInstanceState = savedInstanceState
        setTheme(style)
        contentView.takeIf { it != 0 }?.let {
            setContentView(it)
        }
        viewModelSetup()
        viewSetup()

        baseViewModel?.errorLiveData?.observe(this, Observer {
            hideKeyboard()
            it?.let { it1 -> snackView?.showSnack(it1) }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        if (snackView != null) {
            snackView = null
        }
    }

}