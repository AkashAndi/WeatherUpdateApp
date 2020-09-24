package com.app.weatherupdates.utils

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

fun Fragment.hideFragment(fragment: Fragment?) {
    if (fragment == null) return

    childFragmentManager.inTransaction {
        hide(fragment)
    }
}

fun Fragment.showFragment(fragment: Fragment?) {
    if (fragment == null) return
    childFragmentManager.inTransaction {
        setCustomAnimations(
                android.R.anim.fade_in,
                android.R.anim.fade_out,
                android.R.anim.fade_in,
                android.R.anim.fade_out
        )
        show(fragment)
    }
    hideKeyboard()
}

fun Fragment.pushFragment(
        destinationFragment: Fragment,
        addToBackStack: Boolean = false,
        ignoreIfCurrent: Boolean,
        justAdd: Boolean = true,
        fragmentContainerId: Int
) {
    val currentFragment = childFragmentManager.findFragmentById(fragmentContainerId)
    if (ignoreIfCurrent && currentFragment != null) {
        if (destinationFragment.javaClass.canonicalName.equals(currentFragment.tag, false)) {
            return
        }
    }
    childFragmentManager.inTransaction {
        setCustomAnimations(
                android.R.anim.fade_in,
                android.R.anim.fade_out,
                android.R.anim.fade_in,
                android.R.anim.fade_out
        )
        if (currentFragment != null) {
            hide(currentFragment)
        }
        if (addToBackStack) {
            addToBackStack(destinationFragment.javaClass.canonicalName)
        }
        if (justAdd) {
            add(
                    fragmentContainerId,
                    destinationFragment,
                    destinationFragment.javaClass.canonicalName
            )
        } else {
            replace(
                    fragmentContainerId,
                    destinationFragment,
                    destinationFragment.javaClass.canonicalName
            )
        }
    }
    hideKeyboard()
}


fun Fragment.hideKeyboard() {
    activity?.window?.setSoftInputMode(android.view.WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
    val view = activity?.currentFocus
    if (view != null) {
        val imm =
                activity?.getSystemService(android.content.Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}

fun Activity.hideKeyboard() {
    window.setSoftInputMode(android.view.WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
    val view = currentFocus
    if (view != null) {
        val imm =
                getSystemService(android.content.Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}

fun Fragment.showKeyboard() {
    activity?.window?.setSoftInputMode(android.view.WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)
    val view = activity?.currentFocus
    if (view != null) {
        val imm =
                activity?.getSystemService(android.content.Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
    }
}

fun Activity.showKeyboard() {
    window?.setSoftInputMode(android.view.WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)
    val view = currentFocus
    if (view != null) {
        val imm =
                getSystemService(android.content.Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
    }
}

/**
 * this function will help to open app setting using Intent.
 */
fun AppCompatActivity.openAppSetting() {
    val intent = Intent()
    intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
    val uri = Uri.fromParts("package", packageName, null)
    intent.data = uri
    startActivity(intent)
}

/**
 * this function will help to see if permission is allowed or not with respect to Android OS as well.
 */
fun AppCompatActivity.checkPermissionAllowed(permission: String): Boolean {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
    } else {
        true
    }
}

