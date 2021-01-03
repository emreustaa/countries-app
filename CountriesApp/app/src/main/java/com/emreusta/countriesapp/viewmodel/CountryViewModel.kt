package com.emreusta.countriesapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.emreusta.countriesapp.model.Country

class CountryViewModel : ViewModel() {

    val countryLiveData = MutableLiveData<Country>()

    fun getDataFromRoom() {
        val country = Country("Turkey", "Europe", "Ankara", "TRY", "Turkish", "www.ss.com")
        countryLiveData.value = country
    }

}