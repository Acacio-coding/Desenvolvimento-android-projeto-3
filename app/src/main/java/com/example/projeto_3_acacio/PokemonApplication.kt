package com.example.projeto_3_acacio

import android.app.Application
import com.example.projeto_3_acacio.data.local.PokemonDatabase
import com.example.projeto_3_acacio.data.repository.PokemonRepository


class PokemonApplication: Application() {

    private val database: PokemonDatabase by lazy {
        PokemonDatabase.getInstance(this)
    }

    val repository: PokemonRepository by lazy {
        PokemonRepository(database.pokemonDao())
    }
}