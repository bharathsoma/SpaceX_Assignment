package com.sample.spacexassignment.ui.fragment.detailsfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import com.sample.spacexassignment.R
import com.sample.spacexassignment.adapter.recyclerviewadapter.DetailsRecyclerViewAdapter
import com.sample.spacexassignment.data.extension.orDefaultPlaceHolder
import com.sample.spacexassignment.data.model.DetailItem
import com.sample.spacexassignment.data.networkmodel.LaunchResponse
import com.sample.spacexassignment.databinding.FragmentDetailsBinding
import com.sample.spacexassignment.extension.isTablet
import com.sample.spacexassignment.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : BaseFragment() {

    private var binding: FragmentDetailsBinding? = null

    private val detailsList = mutableListOf<DetailItem>()
    private val adapter = DetailsRecyclerViewAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (requireActivity().isTablet().not()) {
            val args = getArgValues()
            requireActivity().findViewById<Toolbar>(R.id.toolbar)?.title = args?.data?.missionName
            setToolbar(true, args?.data?.missionName.orDefaultPlaceHolder())
        }
        initViews()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            findNavController().popBackStack()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getArgValues(): DetailsFragmentArgs? {
        return if (requireActivity().isTablet().not()) {
            val args: DetailsFragmentArgs? by navArgs()
            args
        } else {
            null
        }
    }

    private fun getLaunchResponse(): LaunchResponse? {
        return arguments?.getParcelable(PARAM_RESPONSE)
    }

    private fun initViews() {
        detailsList.clear()
        if (requireActivity().isTablet().not()) {
            detailsList.addAll(getArgValues()?.data?.getDetails().orEmpty())
        } else {
            detailsList.addAll(getLaunchResponse()?.getDetails().orEmpty())
        }

        binding?.rvDetails?.apply {
            adapter = this@DetailsFragment.adapter.apply {
                setData(detailsList)
            }
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }

    companion object {
        private const val PARAM_RESPONSE = "date"
        fun newInstance(launchResponse: LaunchResponse) = DetailsFragment().apply {
            arguments = bundleOf(
                PARAM_RESPONSE to launchResponse
            )
        }
    }
}
