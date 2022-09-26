package com.sample.spacexassignment.listener

import com.sample.spacexassignment.data.networkmodel.LaunchResponse

interface OnLaunchItemClick {
    fun onItemClick(launchResponse: LaunchResponse, position: Int)
}