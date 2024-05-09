package com.example.rickandmortyrecruitapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private val retrofit =
    Retrofit
        .Builder()
        .baseUrl("https://rickandmortyapi.com/api/")
        .addConverterFactory(
            GsonConverterFactory
                .create()
        )
        .build()

val characterService = retrofit.create(ApiService::class.java)

interface ApiService {
    @GET("character")
    suspend fun getCharacters(): CharactersResponse
}