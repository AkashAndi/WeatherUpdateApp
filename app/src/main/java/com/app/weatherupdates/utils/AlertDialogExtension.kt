package com.app.weatherupdates.utils

import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment

fun Fragment.showAlertDialog(
        title: String? = "", message: String,
        positiveText: String? = "Ok", accepted: ((DialogInterface, Int) -> () -> Unit)? = null,
        cancellable: Boolean = true
) {
    AlertDialog.Builder(requireContext())
            .setCancelable(cancellable)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(positiveText) { dialog, which -> accepted?.invoke(dialog, which) }
            .show()
}

