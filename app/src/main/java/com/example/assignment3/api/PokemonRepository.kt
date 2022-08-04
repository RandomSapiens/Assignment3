package com.example.assignment3.api

import com.example.assignment3.data.PokemonResponse
import com.example.assignment3.data.Result
import retrofit2.Response

interface PokemonRepository {
    suspend fun getPokemon(page:Int): List<Result>
}