package com.emreusta.countriesapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.emreusta.countriesapp.R
import com.emreusta.countriesapp.model.Country
import com.emreusta.countriesapp.util.downloadFromUrl
import com.emreusta.countriesapp.util.placeHolderProgressBar
import com.emreusta.countriesapp.view.FeedFragmentDirections

class CountryAdapter(val countryList: ArrayList<Country>) :
    RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    inner class CountryViewHolder(var view: View) :
        RecyclerView.ViewHolder(view) {

        var imageViewCountry: ImageView
        var textViewCountryName: TextView
        var textViewCountryRegion: TextView

        init {
            imageViewCountry = view.findViewById(R.id.imageViewCountry)
            textViewCountryName = view.findViewById(R.id.textViewCountryName)
            textViewCountryRegion = view.findViewById(R.id.textViewCountryRegion)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        // Layout ile adaptörün birbirine bağlandığı yer
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_country, parent, false)

        return CountryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        // kullanılan layouttaki itemler'a ulaşabilmemizi sağlar
        val country = countryList[position]

        holder.textViewCountryName.text = country.countryName
        holder.textViewCountryRegion.text = country.countryRegion

        holder.itemView.setOnClickListener {
            val action =
                FeedFragmentDirections.actionFeedFragmentToCountryFragment(countryUuid = countryList[position].uuid)
            // action.countryUUID
            Navigation.findNavController(it).navigate(action)
        }

        holder.imageViewCountry.downloadFromUrl(
            countryList[position].url,
            placeHolderProgressBar(holder.view.context)
        )
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