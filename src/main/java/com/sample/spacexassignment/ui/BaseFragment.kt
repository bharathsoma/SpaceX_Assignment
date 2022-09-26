package com.sample.spacexassignment.ui

import android.os.Bundle
import androidx.activity.addCallback
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.sample.spacexassignment.NavigationManager
import com.sample.spacexassignment.R
import com.sample.spacexassignment.extension.isTablet
import javax.inject.Inject

open class BaseFragment : Fragment() {

    var navigationManager: NavigationManager? = null
        @Inject set

    private var canNavigateBack: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback {
            if (canNavigateBack) {
                findNavController().navigateUp()
            }
        }
    }

    fun setToolbar(canNavigateBack: Boolean, title: String) {
        if (requireActivity().isTablet().not()) {
            this.canNavigateBack = canNavigateBack
            setHasOptionsMenu(canNavigateBack)
            with(requireActivity().findViewById<Toolbar>(R.id.toolbar)) {
                this.title = title
                navigationIcon = if (canNavigateBack) {
                    ContextCompat.getDrawable(requireContext(), R.drawable.ic_arrow_back)
                } else null
            }
        }
    }
}