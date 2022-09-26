package com.sample.spacexassignment.ui.fragment.launchfragment

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sample.spacexassignment.data.networkmodel.LaunchResponse
import com.sample.spacexassignment.network.remote.RemoteRepository
import com.sample.spacexassignment.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LaunchViewModel @Inject constructor(
    networkRepository: RemoteRepository, application: Application
) : BaseViewModel(networkRepository, application) {

    private val _response: MutableLiveData<List<LaunchResponse>> = MutableLiveData()
    val response: LiveData<List<LaunchResponse>> = _response

    var currentSelectedItem: Int? = null

    fun fetchLaunches(forceUpdate: Boolean = false) = viewModelScope.launch {
        if (_response.value.isNullOrEmpty() || forceUpdate) {
            showLoading()
            remoteRepository?.getLaunches()?.collect { values ->
                hideLoading()
                _response.value = values.data
            }
        }
    }
}
