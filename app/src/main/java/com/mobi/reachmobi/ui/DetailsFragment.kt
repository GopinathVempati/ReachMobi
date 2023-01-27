package com.mobi.reachmobi.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.mobi.reachmobi.R
import com.mobi.reachmobi.databinding.FragmentDetailsBinding
import com.mobi.reachmobi.utils.BaseFragment
import com.mobi.reachmobi.viewmodels.SportsViewModel

class DetailsFragment : BaseFragment() {

    private val viewModel: SportsViewModel by activityViewModels()
    lateinit var detailsBinding: FragmentDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        detailsBinding = FragmentDetailsBinding.inflate(inflater, container, false)
        return detailsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        detailsBinding.sportViewModel = viewModel
    }
}