package com.example.assignment3

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.assignment3.api.PokemonRepository
import com.example.assignment3.api.PokemonSource
import com.example.assignment3.data.Result
import kotlinx.coroutines.flow.Flow

class MainViewModel(private val pokemonRepository: PokemonRepository):ViewModel() {
    fun getPokemonPagination(): Flow<PagingData<Result>>{
        val items= Pager(PagingConfig(pageSize = 1)){
            PokemonSource(pokemonRepository)
        }.flow.cachedIn(viewModelScope)
        return items
    }
}
