package com.emreusta.countriesapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.emreusta.countriesapp.databinding.FragmentCountryBinding
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
        binding = FragmentCountryBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(CountryViewModel::class.java)
        viewModel.getDataFromRoom()

        arguments?.let {
            countryUuid = CountryFragmentArgs.fromBundle(it).countryUuid
        }


    }

    private fun observeLiveData() {
        viewModel.countryLiveData.observe(viewLifecycleOwner, Observer { country ->
            country?.let {
                binding.textViewCountryNameDetail.text = country.countryName
                binding.textViewCountryCapitalDetail.text = country.countryCapital
                binding.textViewCountryCurrencyDetail.text = country.countryCurrency
                binding.textViewCountryLanguageDetail.text = country.countryLanguage
                binding.textViewCountryRegionDetail.text = country.countryRegion
            }
        })
    }

}