package com.mobi.reachmobi.viewmodels

import androidx.databinding.BaseObservable
import com.mobi.reachmobi.models.CountriesList
import com.mobi.reachmobi.utils.checkNull

class SportsItemViewModel(private val countriesListItem: CountriesList) : BaseObservable() {
    fun getCountryName() = countriesListItem.strCountry.checkNull("-NA-")

    fun getSportName() = countriesListItem.strSport.checkNull("-NA-")

    fun getLeagueName() = countriesListItem.strLeagueAlternate.checkNull("-NA-")

    fun getCurrentSeason() = countriesListItem.strCurrentSeason.checkNull("-NA-")
}