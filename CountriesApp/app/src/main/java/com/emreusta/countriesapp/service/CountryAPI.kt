package com.emreusta.countriesapp.service

import com.emreusta.countriesapp.model.Country
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.POST

interface CountryAPI {

    // GET,POST
    // https://raw.githubusercontent.com/atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json
    // BASE_URL ->https://raw.githubusercontent.com/
    // EXTENSION -> atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json

    // Single -> Sadece bir defa işlemi yapar ve durur
    @GET("atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json")
    fun getCountries(): Single<List<Country>>

}