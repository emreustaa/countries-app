package com.emreusta.countriesapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.emreusta.countriesapp.R

import com.emreusta.countriesapp.databinding.FragmentCountryBinding
import com.emreusta.countriesapp.util.downloadFromUrl
import com.emreusta.countriesapp.util.placeHolderProgressBar
import com.emreusta.countriesapp.viewmodel.CountryViewModel


class CountryFragment : Fragment() {
    private var countryUuid = 0
    private lateinit var viewModel: CountryViewModel
    private lateinit var binding: FragmentCountryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_country, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            countryUuid = CountryFragmentArgs.fromBundle(it).countryUuid
        }

        viewModel = ViewModelProviders.of(this).get(CountryViewModel::class.java)
        viewModel.getDataFromRoom(countryUuid)
        observeLiveData()


    }

    private fun observeLiveData() {
        viewModel.countryLiveData.observe(viewLifecycleOwner, Observer { country ->
            country?.let {
                binding.apply {
                    selectedCountry = country
                }
            }
        })
    }

}