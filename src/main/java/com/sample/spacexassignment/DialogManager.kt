package com.sample.spacexassignment

import android.app.Activity
import android.app.AlertDialog

class DialogManager {
    fun showDialog(
        activity: Activity, title: String, message: String, ctaText: String
    ): AlertDialog? {
        return AlertDialog.Builder(activity).setTitle(title).setMessage(message).setPositiveButton(
            ctaText
        ) { view, _ ->
            view.dismiss()
        }.show()
    }
}
