package com.example.assignment3

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.example.assignment3.data.Result


@Composable
fun DisplayPokemon(viewModel: MainViewModel) {
    val pokemons = viewModel.getPokemonPagination().collectAsLazyPagingItems()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),

            ) {
            Image(
                painter = painterResource(id = R.drawable.img_2),
                contentDescription = "",
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        LazyRow {
//            Log.d("Uncer10ity","${pokemons.itemSnapshotList.size}")

//            val staticPokemons=pokemons.itemSnapshotList
//            if(staticPokemons.size<=15){
//                items(staticPokemons){ pokemon ->
//                    PokemonImageCard(pokemon = pokemon)
//                }
//            }else{
//                items(staticPokemons.size){
//                    index->PokemonImageCard(pokemon = staticPokemons[index%15])
//                }
//            }

            itemsIndexed(pokemons) { index, pokemon ->
                PokemonImageCard(pokemon = pokemons.get(index % 15))
            }
//            items(pokemons) { pokemon ->
//                PokemonImageCard(pokemon = pokemon)
//            }
            pokemons.apply {
                when {
                    loadState.refresh is LoadState.Loading -> item {
                        ShowProgressBar()
                    }
                    loadState.append is LoadState.Loading -> {
                        item { ShowProgressBar() }
                    }
                }
            }
        }
    }
}

@Composable
fun PokemonImageCard(pokemon: Result?) {

//    Log.d("Uncer10ity here i am ", "$pokemon")

    Card(
        elevation = 5.dp,
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier
            .width(300.dp)
            .padding(4.dp)
    ) {
        Box(
            modifier = Modifier
                .height(300.dp)
                .fillMaxWidth()
        ) {

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(pokemon?.url)
                    .placeholder(R.drawable.img_1)
                    .error(R.drawable.ic_baseline_error_24)
                    .crossfade(500)
                    .transformations(
                        CircleCropTransformation(),
//                        RoundedCornersTransformation()

                    )
                    .build(),
                contentDescription = stringResource(R.string.description),
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize(),
                onLoading = {
                    Log.d("Uncer10ity", "${pokemon?.name} Image is in Loading state")
                }

            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black
                            ),
                            startY = 600f
                        )
                    )
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                contentAlignment = Alignment.BottomCenter
            ) {
                pokemon?.let {
                    Text(
                        text = it.name,
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }
        }
    }
}


@Composable
fun ShowProgressBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
        Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
//    PokemonImageCard(
//        painter = painterResource(id = R.drawable.img),
//        contentDescription = "",
//        imageTitle = "SUPER SONIC POKEMON"
//    )

}


