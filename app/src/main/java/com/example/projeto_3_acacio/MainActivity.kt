package com.example.projeto_3_acacio

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.projeto_3.views.PokemonListScreen
import com.example.projeto_3.views.PokemonVMFactory
import com.example.projeto_3.views.PokemonViewModel
import com.example.projeto_3_acacio.ui.theme.Projeto3acacioTheme
import com.example.projeto_3_acacio.views.PokemonDetailsScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val pokemonViewModel by viewModels<PokemonViewModel>() {
            PokemonVMFactory(
                (this.applicationContext as PokemonApplication).repository
            )
        }

        setContent {
            Projeto3acacioTheme(){
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Projeto3(pokemonViewModel = pokemonViewModel)
                }
            }
        }
    }
}

@Composable
fun Projeto3(
    pokemonViewModel: PokemonViewModel
) {
    val navController = rememberNavController()

    Scaffold {
        NavHost(
            navController = navController,
            startDestination = "${R.string.pokemon_list_screen}"
        ) {
            composable(route = "${R.string.pokemon_list_screen}") {
                PokemonListScreen(pokemonViewModel = pokemonViewModel,
                    navController = navController)
            }

            composable(
                route = "${R.string.pokemon_details_screen}?id={id}",
                arguments = listOf(navArgument("id") {
                    defaultValue = -1
                    type = NavType.IntType
                })
            ) {
                val id = it.arguments?.getInt("id") ?: -1
                val pokemon = pokemonViewModel.getPokemon(id)
                
                PokemonDetailsScreen(pokemon = pokemon)
            }
        }
    }
}

