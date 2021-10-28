package com.emreusta.countriesapp.adapter

import android.view.View
import com.emreusta.countriesapp.model.Country

interface CountryClickListener {
    fun onCountryClicked(uuid : Int)
}