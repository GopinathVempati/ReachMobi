package com.mobi.reachmobi.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.mobi.reachmobi.databinding.FragmentSportsBinding
import com.mobi.reachmobi.models.CountriesList
import com.mobi.reachmobi.network.APIResult
import com.mobi.reachmobi.utils.*
import com.mobi.reachmobi.viewmodels.SportsViewModel

class SportsFragment : BaseFragment() {

    private val viewModel: SportsViewModel by activityViewModels()
    lateinit var binding: FragmentSportsBinding
    lateinit var adapter: SportsAdapter
    var countriesList: ArrayList<CountriesList> = ArrayList()
    var countryName: String = "United States"
    var sportName: String = "Soccer"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSportsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.sportsRv.addItemDecoration(getItemDecoration(right = 8, top = 16, bottom = 16, left = 8))
        initUpdateAdapter(countriesList)
        initObservers()
        showProgressBar()
        apiCall { viewModel.getAllCountriesList(countryName, sportName) }
        searchView()
    }

    private fun searchView() {
        binding.sportsSearchView.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                if (binding.countrySearchView.getTrimmedText().length > 2 && binding.sportsSearchView.getTrimmedText().length > 2) {
                    apiCall {
                        viewModel.getAllCountriesList(
                            binding.countrySearchView.getTrimmedText(),
                            binding.sportsSearchView.getTrimmedText()
                        )
                    }
                    hideKeyboard()
                    showProgressBar()
                } else {
                    displaySnackBar(binding.root)
                }
                return@OnEditorActionListener true
            }
            false
        })

        binding.countrySearchView.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                if (binding.countrySearchView.getTrimmedText().length > 2) {
                    apiCall {
                        viewModel.getAllCountriesList(
                            binding.countrySearchView.getTrimmedText(),
                            binding.sportsSearchView.getTrimmedText()
                        )
                    }
                    hideKeyboard()
                    showProgressBar()
                } else {
                    displaySnackBar(binding.root)
                }
                return@OnEditorActionListener true
            }
            false
        })
    }

    private fun initObservers() {
        viewModel.countriesList.observe(viewLifecycleOwner) {
            when (it) {
                is APIResult.Success -> {
                    if (it.data.isSuccessful) {
                        it.data.body()?.let { response ->
                            response.countries?.let { it1 -> initUpdateAdapter(it1) }
                        }
                    }
                }
                is APIResult.Error -> {
                }
            }
            hideProgressBar()
        }
    }

    private fun initUpdateAdapter(countries: List<CountriesList>) {
        adapter = SportsAdapter(countries, object : SportsAdapter.SportsListener {
            override fun onItemClick(countriefsList: CountriesList) {
                viewModel.setCountryItem(countriefsList)
                findNavController().navigate(SportsFragmentDirections.actionSportsFragmentToDetailsFragment())
            }
        })
        binding.sportsRv.adapter = adapter
    }
}