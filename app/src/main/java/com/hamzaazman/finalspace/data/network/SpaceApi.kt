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
    suspend fun getAllCharacters(): List<Character>

    @GET("episode/")
    suspend fun getAllEpisode(): List<Episode>

    @GET("location/")
    suspend fun getAllLocations(): List<Location>

    @GET
    suspend fun getCharacterByLocation(
        @Url url: String
    ): Character

    @GET("quote/")
    suspend fun getAllQuote(): List<Quote>
}