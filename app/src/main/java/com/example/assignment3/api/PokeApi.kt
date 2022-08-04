package com.example.assignment3.api

import com.example.assignment3.data.PokemonResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface PokeApi {
    @GET("pokemon")
    suspend fun getPokemon(
        @Query("offset")
        offset:Int,
        @Query("limit")
        limit:Int=5
    ): PokemonResponse
}

// imageurl::- coil  dpendency ad krna,
//paging3 custom pagination