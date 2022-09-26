package com.sample.spacexassignment.ui

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sample.spacexassignment.network.remote.RemoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
open class BaseViewModel @Inject constructor(
    val remoteRepository: RemoteRepository? = null,
    application: Application
) : AndroidViewModel(application) {

    private val _loading: MutableLiveData<Int> = MutableLiveData(View.GONE)
    val loading: LiveData<Int> = _loading

    fun showLoading() {
        _loading.postValue(View.VISIBLE)
    }

    fun hideLoading() {
        _loading.postValue(View.GONE)
    }
}