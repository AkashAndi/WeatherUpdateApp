package com.app.weatherupdates.utils

import android.content.Context
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.TranslateAnimation
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar


fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.enable() {
    this.isEnabled = true
}

fun View.disable() {
    this.isEnabled = false
}

fun makeLongToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}

fun makeShortToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
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


fun View.slideUp(duration: Long) {
    val animate = TranslateAnimation(
            0f,  // fromXDelta
            0f,  // toXDelta
            this.height.toFloat(),  // fromYDelta
            this.height.toFloat().minus(200)) // toYDelta
    animate.duration = duration
    animate.fillAfter = true
    this.startAnimation(animate)
}

fun View.slideDown(duration: Long) {
    val animate = TranslateAnimation(
            0f,  // fromXDelta
            0f,  // toXDelta
            0f,  // fromYDelta
            200f) // toYDelta
    animate.duration = duration
    animate.fillAfter = true
    this.startAnimation(animate)
}

fun View.fadeIn(duration: Long) {
    val animate = AlphaAnimation(0.2f, 1f)
    animate.duration = duration
    this.startAnimation(animate)
}