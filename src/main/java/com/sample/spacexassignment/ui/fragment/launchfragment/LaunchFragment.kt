package com.sample.spacexassignment.ui.fragment.launchfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import com.sample.spacexassignment.R
import com.sample.spacexassignment.adapter.recyclerviewadapter.LaunchRecyclerViewAdapter
import com.sample.spacexassignment.data.model.LaunchListItem
import com.sample.spacexassignment.databinding.FragmentLaunchBinding
import com.sample.spacexassignment.extension.isTablet
import com.sample.spacexassignment.listener.OnLaunchItemClick
import com.sample.spacexassignment.listener.OnRecyclerViewItemClick
import com.sample.spacexassignment.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LaunchFragment : BaseFragment(), OnRecyclerViewItemClick {

    private val launchViewModel by viewModels<LaunchViewModel>()
    private var binding: FragmentLaunchBinding? = null

    private val launchListList = mutableListOf<LaunchListItem>()
    private val adapter = LaunchRecyclerViewAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLaunchBinding.inflate(inflater, container, false)
        binding?.lifecycleOwner = viewLifecycleOwner
        binding?.viewModel = launchViewModel
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolbar(false, getString(R.string.app_name))
        initViews()
        initObservers()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onItemClick(position: Int) {
        launchViewModel.currentSelectedItem = position
        adapter.highlightItem(position)

        launchViewModel.response.value?.get(position)?.let {
            if (requireActivity().isTablet().not()) {
                navigationManager?.navigateToDetailsFragment(it)
            } else {
                (requireActivity() as? OnLaunchItemClick)?.onItemClick(it, position)
            }
        }
    }

    private fun initViews() {
        launchViewModel.fetchLaunches()
        binding?.rvLaunch?.apply {
            adapter = this@LaunchFragment.adapter.apply {
                setData(launchListList)
            }
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }

    private fun initObservers() {
        launchViewModel.response.observe(viewLifecycleOwner) {
            launchListList.clear()
            it?.map { launchItem ->
                launchItem.toLaunchListItem()
            }?.forEach { launchListItem ->
                launchListList.add(launchListItem)
            }
            adapter.setData(launchListList)
            launchViewModel.currentSelectedItem?.let {
                adapter.highlightItem(it)
            }
        }
    }
}