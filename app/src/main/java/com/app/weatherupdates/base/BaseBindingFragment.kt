package com.app.weatherupdates.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.app.weatherupdates.utils.hideKeyboard
import com.app.weatherupdates.utils.showSnack

abstract class BaseBindingFragment<B : ViewDataBinding> : BaseFragment() {

    var binding: B? = null

    var baseViewModel: BaseObservableViewModel? = null

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, contentView, container, false)
        return binding?.root
    }

    override fun onDestroyView() {
        binding?.unbind()
        super.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        baseViewModel?.errorLiveData?.observe(this, {
            hideKeyboard()
            it?.let { it1 -> binding?.root?.showSnack(it1) }
        })
    }

}