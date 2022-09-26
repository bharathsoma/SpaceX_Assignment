package com.sample.spacexassignment

import android.app.Activity
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import com.sample.spacexassignment.data.networkmodel.LaunchResponse
//import com.sample.spacexassignment.data.networkmodel.LaunchResponse
import javax.inject.Inject

class NavigationManager @Inject constructor(
    private val activity: Activity
) {
    fun navigateToDetailsFragment(launchResponse: LaunchResponse) {
        activity.findNavController(R.id.nav_host_fragment_activity_main).navigate(
            R.id.navigation_details_fragment, bundleOf(
                "data" to launchResponse
            )
        )
    }
}
