package com.example.assignment3
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import com.example.assignment3.api.IPokemonRepository
import com.example.assignment3.api.RetrofitClient


class MainActivity : ComponentActivity() {
    private lateinit var viewModel:MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpViewModel()
        setContent {
            DisplayPokemon(viewModel = viewModel)
        }
    }
    private fun setUpViewModel(){
        viewModel=ViewModelProvider(
            this,
            ViewModelFactory(IPokemonRepository(RetrofitClient.api))
        )[MainViewModel::class.java]
    }

}




