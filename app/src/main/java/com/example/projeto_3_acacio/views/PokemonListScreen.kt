package com.example.projeto_3.views

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.projeto_3_acacio.R
import com.example.projeto_3_acacio.data.domain.Pokemon

@Composable
fun PokemonListScreen(
    navController: NavController,
    pokemonViewModel: PokemonViewModel
) {
    val pokemons by pokemonViewModel.pokemons.observeAsState(listOf())
    PokemonList(pokemons = pokemons, navController = navController)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PokemonList(
    navController: NavController,
    pokemons: List<Pokemon>
) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(2),
        modifier = Modifier.background(Color.Black)
    ) {
        items(pokemons) { pokemon ->
            PokemonEntry(pokemon = pokemon, onDetails = {
                navController.navigate("${R.string.pokemon_details_screen}?id=${pokemon.number}")
            })
        }
    }
}

@Composable
fun PokemonEntry(
    onDetails: () -> Unit,
    pokemon: Pokemon
) {
    val density = LocalDensity.current.density

    val width = remember {
        mutableStateOf(0F)
    }

    val height = remember {
        mutableStateOf(0F)
    }

    Card(
        modifier = Modifier
            .padding(6.dp)
            .clickable {
                onDetails()
            },
        elevation = 8.dp
    ) {
        Box {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RectangleShape)
                    .onGloballyPositioned {
                        width.value = it.size.width / density
                        height.value = it.size.height / density
                    },

                model = ImageRequest.Builder(LocalContext.current)
                    .data(pokemon.sprite)
                    .crossfade(true)
                    .build(),

                error = painterResource(R.drawable.pokeball),

                placeholder = painterResource(R.drawable.pokeball),

                onError = {
                  Log.d("Image Error", it.result.throwable.toString())
                },

                contentDescription = pokemon.name,
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .size(width.value.dp, height.value.dp)
                    .background(
                        Brush.verticalGradient(
                            listOf(Color.Transparent, Color.Black),
                            100F,
                            650F
                        )
                    )
            )
            Text(
                text = pokemon.name,
                modifier = Modifier.align(Alignment.BottomCenter),
                style = MaterialTheme.typography.h5.copy(
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}