package com.emreusta.countriesapp.view

import android.os.Binder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.emreusta.countriesapp.adapter.CountryAdapter
import com.emreusta.countriesapp.databinding.FragmentFeedBinding
import com.emreusta.countriesapp.model.Country

import com.emreusta.countriesapp.viewmodel.FeedViewModel


class FeedFragment : Fragment() {

    private lateinit var viewModel: FeedViewModel
    private val countryAdapter = CountryAdapter(arrayListOf())
    private lateinit var binding: FragmentFeedBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentFeedBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(FeedViewModel::class.java)
        viewModel.refreshData()

        binding.recyclerViewCountryList.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewCountryList.adapter = countryAdapter


        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.recyclerViewCountryList.visibility = View.GONE
            binding.textViewCountryError.visibility = View.GONE
            binding.progressBarCountryLoading.visibility = View.VISIBLE
            viewModel.refreshFromAPI()
            binding.swipeRefreshLayout.isRefreshing = false

        }
        observeLiveData()
        /*
        binding.buttonFeed.setOnClickListener {
            val action = FeedFragmentDirections.actionFeedFragmentToCountryFragment()
            Navigation.findNavController(it).navigate(action)
        }*/
    }

    private fun observeLiveData() {
        viewModel.countries.observe(viewLifecycleOwner, Observer { countries ->
            countries?.let {
                binding.recyclerViewCountryList.visibility = View.VISIBLE
                countryAdapter.updateCountryList(countries)
            }

        })

        viewModel.countryError.observe(viewLifecycleOwner, Observer { error ->
            error?.let {
                if (it) {
                    binding.textViewCountryError.visibility = View.VISIBLE
                } else {
                    binding.textViewCountryError.visibility = View.GONE
                }
            }
        })

        viewModel.countryLoading.observe(viewLifecycleOwner, Observer { loading ->
            loading?.let {
                if (it) {
                    binding.progressBarCountryLoading.visibility = View.VISIBLE
                    binding.recyclerViewCountryList.visibility = View.GONE
                    binding.textViewCountryError.visibility = View.GONE
                } else {
                    binding.progressBarCountryLoading.visibility = View.GONE
                }

            }
        })
    }

}
