package com.example.assignment3.api

import com.example.assignment3.data.PokemonResponse
import com.example.assignment3.data.Result
import retrofit2.Response

class IPokemonRepository(private val api: PokeApi) :PokemonRepository{
    override suspend fun getPokemon(page: Int): List<Result> {
        return api.getPokemon(page).results
    }
}