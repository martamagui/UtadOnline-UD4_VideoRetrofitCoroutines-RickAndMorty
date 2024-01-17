package com.mmag.ud4_videoretrofitconcoroutines.network

import com.mmag.ud4_videoretrofitconcoroutines.network.model.AllCharactersResponse
import retrofit2.Response
import retrofit2.http.GET

interface RickAndMortyService {

    @GET("character")
    suspend fun getAllCharacters(): Response<AllCharactersResponse>
}