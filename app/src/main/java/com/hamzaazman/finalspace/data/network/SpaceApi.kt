package com.hamzaazman.finalspace.data.network

import com.hamzaazman.finalspace.model.Character
import com.hamzaazman.finalspace.model.Episode
import com.hamzaazman.finalspace.model.Location
import com.hamzaazman.finalspace.model.Quote
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface SpaceApi {
    @GET("character/")
    suspend fun getAllCharacters(): Response<List<Character>>

    @GET("episode/")
    suspend fun getAllEpisode(): Response<List<Episode>>

    @GET("location/")
    suspend fun getAllLocations(): Response<List<Location>>

    @GET
    suspend fun getCharacterByLocation(
        @Url url: String
    ): Response<Character>

    @GET("quote/")
    suspend fun getAllQuote(): Response<List<Quote>>
}