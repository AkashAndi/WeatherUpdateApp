package com.app.weatherupdates.utils

import android.view.View
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar


fun View.gone() {
    this.visibility = View.GONE
}

fun View.showSnack(message: String, length: Int = Snackbar.LENGTH_LONG) {
    val snack = Snackbar.make(this, message, length).apply {
        view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text).maxLines = 5
    }
    snack.show()
}

fun View.avoidDoubleClicks() {
    if (!this.isClickable) {
        return
    }
    this.isClickable = false
    this.postDelayed({ this.isClickable = true }, 2000)
}


