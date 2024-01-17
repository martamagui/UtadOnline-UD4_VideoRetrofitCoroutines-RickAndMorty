package com.mmag.ud4_videoretrofitconcoroutines.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIManager {

    //Conversor Json-> data class
    private val converter = GsonConverterFactory.create()

    //Intercepta las llamadas por consola
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    //Acopla al cliente el interceptor/ carga el interceptor
    private val client = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()

    //Instancia de Retrofit
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://rickandmortyapi.com/api/") //Tiene que terminar en /
        .client(client)
        .addConverterFactory(converter)
        .build()

    //Atraves del service podemos llamar a las peticiones de red del servicio de RickAndMortyService
    val service: RickAndMortyService by lazy {
        retrofit.create(RickAndMortyService::class.java)
    }
}