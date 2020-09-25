package com.app.weatherupdates.ui.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.app.weatherupdates.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

object BindingsAdapter {

    @JvmStatic
    @BindingAdapter("loadImage")
    fun loadImage(view: ImageView, url: String?) {
        Glide.with(view)
                .load(url)
                .placeholder(R.drawable.ic_placeholder)
                .error(R.drawable.ic_placeholder)
                .transform(CenterCrop(), RoundedCorners(10))
                .into(view)
    }

    @JvmStatic
    @BindingAdapter("loadImage")
    fun loadImage(view: ImageView, url: Int?) {
        Glide.with(view)
                .load(url)
                .placeholder(R.drawable.ic_placeholder)
                .error(R.drawable.ic_placeholder)
                .into(view)
    }
}