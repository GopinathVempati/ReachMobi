package com.mobi.reachmobi.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mobi.reachmobi.R
import com.mobi.reachmobi.databinding.ItemCountryBinding
import com.mobi.reachmobi.models.CountriesList
import com.mobi.reachmobi.viewmodels.SportsItemViewModel

class SportsAdapter(private val countriesList: List<CountriesList>,val sportsListener: SportsListener) : RecyclerView.Adapter<SportsAdapter.SportsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SportsViewHolder {
        val binding: ItemCountryBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_country,
            parent,
            false
        )
        return SportsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SportsViewHolder, position: Int) {
        holder.bind(countriesList[position],sportsListener)
    }

    override fun getItemCount(): Int  = countriesList.size

    class SportsViewHolder(private val binding: ItemCountryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(countriesListItem: CountriesList, sportsListener: SportsListener) {
            binding.itemViewModel = SportsItemViewModel(countriesListItem)
            itemView.setOnClickListener { sportsListener.onItemClick(countriesListItem) }
        }
    }

    interface SportsListener {
        fun onItemClick(countriesList: CountriesList)
    }
}