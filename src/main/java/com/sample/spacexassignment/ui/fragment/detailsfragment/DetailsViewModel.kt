package com.sample.spacexassignment.ui.fragment.detailsfragment

import android.app.Application
import com.sample.spacexassignment.network.remote.RemoteRepository
import com.sample.spacexassignment.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    networkRepository: RemoteRepository,
    application: Application
) : BaseViewModel(networkRepository, application)
