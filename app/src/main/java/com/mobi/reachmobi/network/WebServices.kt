package com.mobi.reachmobi.network

import com.mobi.reachmobi.models.AllLeaguesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface WebServices {
    @Headers("Content-Type: application/json")
    @GET("search_all_leagues.php")
    suspend fun getMovieDetails(@Query("c") country: String,@Query("s") OptionalSportName: String?): Response<AllLeaguesResponse>
}
