package com.example.assignment3.api

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.assignment3.data.Result
import com.example.assignment3.utils.Constants.Companion.FILE_TYPE
import com.example.assignment3.utils.Constants.Companion.IMAGE_BASE_URL_HOME
import kotlinx.coroutines.delay

class PokemonSource(private val pokemonRepository: PokemonRepository):PagingSource<Int,Result>(){

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        return try{
            val page=params.key ?: 0
            delay(1000)
            val pokemonResponse=pokemonRepository.getPokemon(page*5).map { pokemon->
//                Log.d("Uncer10ity","$pokemon")
                val urlId=pokemon.url.reversed()
                var imageUrl=""
                for(index in 0..urlId.length){
                    if(index==0)continue
                    if(urlId[index] in '0'..'9')imageUrl+=urlId[index]
                    else break
                }
                imageUrl=imageUrl.reversed()

                imageUrl=IMAGE_BASE_URL_HOME+imageUrl+ FILE_TYPE
                pokemon.url=imageUrl
                pokemon
            }
            Log.d("Uncer10ity","$pokemonResponse")

            LoadResult.Page(
                data= pokemonResponse,
                prevKey = if(page==0)null else page-1,
                nextKey = page.plus(1)
            )
        }catch (e:Exception){
            LoadResult.Error(e)
        }
    }
    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }
}