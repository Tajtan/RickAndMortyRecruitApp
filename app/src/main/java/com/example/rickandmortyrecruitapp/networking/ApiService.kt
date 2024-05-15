package com.example.rickandmortyrecruitapp.networking

import com.example.rickandmortyrecruitapp.data.CharactersResponse
import com.example.rickandmortyrecruitapp.data.EpisodeResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

// Retrofit setup to create Retrofit instance with:
// - Base URL for all API endpoints ,
// - Converter factory for serialization and deserialization of objects,
// - GsonConverterFactory to convert JSON data to Kotlin/Java objects.
private val retrofit =
    Retrofit
        .Builder()
        .baseUrl("https://rickandmortyapi.com/api/")
        .addConverterFactory(
            GsonConverterFactory
                .create()
        )
        .build()

// Creating an implementation of the ApiService interface using the Retrofit instance to make API calls defined in the ApiService interface.
val characterService = retrofit.create(ApiService::class.java)

// Interface specifying GET requests to retrieve characters and episodes data from the API.
// It takes a 'page' parameter which represents number of pages containing data - API returns "info" object with "pages" property.
interface ApiService {
    @GET("character")
    suspend fun getCharacters(@Query("page") page: Int): CharactersResponse

    @GET("episode")
    suspend fun getEpisodes(@Query("page") page: Int): EpisodeResponse
}