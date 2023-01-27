package com.mobi.reachmobi.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobi.reachmobi.models.AllLeaguesResponse
import com.mobi.reachmobi.models.CountriesList
import com.mobi.reachmobi.network.APIResult
import com.mobi.reachmobi.network.ApiClient
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import retrofit2.Response

class SportsViewModel : ViewModel() {

    private var _countriesList: MutableLiveData<APIResult<Response<AllLeaguesResponse>>> =
        MutableLiveData()
    var countriesList: LiveData<APIResult<Response<AllLeaguesResponse>>> = _countriesList

    fun getAllCountriesList(country: String, optionalSportName: String) {
        viewModelScope.launch {
            val response = ApiClient.getService().getMovieDetails(country, if(optionalSportName.isEmpty()) null else optionalSportName)
            _countriesList.postValue(APIResult.Success(response))
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }

    var countriesListItem: CountriesList = CountriesList()
    fun setCountryItem(countries: CountriesList) {
        this.countriesListItem = countries
    }

    fun getCountryName() = countriesListItem.strCountry

    fun getSportName() = countriesListItem.strSport

    fun getLeagueName() = countriesListItem.strLeagueAlternate

    fun getCurrentSeason() = countriesListItem.strCurrentSeason

    fun getDescription() = countriesListItem.strDescriptionEN
}