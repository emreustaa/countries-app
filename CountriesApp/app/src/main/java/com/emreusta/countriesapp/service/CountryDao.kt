package com.emreusta.countriesapp.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.emreusta.countriesapp.model.Country

@Dao
interface CountryDao {

    // Data access

    @Insert
    suspend fun insertAll(vararg countries: Country): List<Long>

    // Insert -> InsertInto
    // suspend -> coroutine, pause & resume
    // varargs -> multiple object keyword
    // List<Long> primary keys returned

    @Query("SELECT * FROM country")
    suspend fun getAllCountries(): List<Country>

    @Query("SELECT * FROM country WHERE uuid= :countryId")
    suspend fun getCountry(countryId: Int): Country

    @Query("DELETE FROM country")
    suspend fun deleteAllCountries()

}