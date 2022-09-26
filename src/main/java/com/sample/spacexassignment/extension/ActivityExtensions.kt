package com.sample.spacexassignment.extension

import android.app.Activity
import com.sample.spacexassignment.R

fun Activity.isTablet(): Boolean {
    return resources.getBoolean(R.bool.isTablet)
}