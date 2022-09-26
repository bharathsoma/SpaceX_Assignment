package com.sample.spacexassignment.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sample.spacexassignment.R
import com.sample.spacexassignment.data.networkmodel.LaunchResponse
import com.sample.spacexassignment.databinding.ActivityMainBinding
import com.sample.spacexassignment.extension.isTablet
import com.sample.spacexassignment.listener.OnLaunchItemClick
import com.sample.spacexassignment.ui.fragment.detailsfragment.DetailsFragment
import com.sample.spacexassignment.ui.fragment.launchfragment.LaunchFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), OnLaunchItemClick {

    private lateinit var binding: ActivityMainBinding
    private val fragmentLaunch = LaunchFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        initViews()
    }

    override fun onItemClick(launchResponse: LaunchResponse, position: Int) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_details, DetailsFragment.newInstance(launchResponse)).commit()
    }

    private fun initViews() {
        if (isTablet()) {
            binding.toolbar.navigationIcon = null
            supportFragmentManager.beginTransaction().replace(R.id.fragment_launch, fragmentLaunch).commit()
        }
    }
}