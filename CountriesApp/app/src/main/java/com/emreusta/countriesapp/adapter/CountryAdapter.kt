package com.emreusta.countriesapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.emreusta.countriesapp.R
import com.emreusta.countriesapp.databinding.ItemCountryBinding
import com.emreusta.countriesapp.model.Country
import com.emreusta.countriesapp.util.downloadFromUrl
import com.emreusta.countriesapp.util.placeHolderProgressBar
import com.emreusta.countriesapp.view.FeedFragmentDirections

class CountryAdapter(private val countryList: ArrayList<Country>) :
    RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        // Layout ile adaptörün birbirine bağlandığı yer
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemCountryBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.item_country,
            parent,
            false
        )

        return CountryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        // kullanılan layouttaki itemler'a ulaşabilmemizi sağlar
        holder.bind(countryList[position])
        holder.binding.root.setOnClickListener {
            val action =
                FeedFragmentDirections.actionFeedFragmentToCountryFragment(countryList[position].uuid)
            Navigation.findNavController(it).navigate(action)
        }
    }

    inner class CountryViewHolder(var binding: ItemCountryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Country) {
            binding.country = item
        }

    }

    override fun getItemCount(): Int {
        //kaç tane row oluşturacağı söyleniyor
        return countryList.size
    }

    fun updateCountryList(newCountryList: List<Country>) {
        countryList.clear()
        countryList.addAll(newCountryList)
        notifyDataSetChanged()
    }


}