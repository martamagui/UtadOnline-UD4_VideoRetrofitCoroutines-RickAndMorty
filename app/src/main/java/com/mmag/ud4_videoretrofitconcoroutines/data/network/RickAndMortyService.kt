package com.mmag.ud4_videoretrofitconcoroutines.data.network

import com.mmag.ud4_videoretrofitconcoroutines.data.network.model.AllCharactersResponse
import retrofit2.Response
import retrofit2.http.GET

interface RickAndMortyService {

    //Marcamos la función como suspend para obligar a lanzarla en una corrutina.
    //Al ser lanzada en una corrutina deberemos poner que devuelve un objeto Response<Tipo de obj que recibimos de la API>
    @GET("character")
    suspend fun getAllCharacters(): Response<AllCharactersResponse>

}