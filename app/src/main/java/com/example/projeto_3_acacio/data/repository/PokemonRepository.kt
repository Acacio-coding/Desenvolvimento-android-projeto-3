package com.example.projeto_3_acacio.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.projeto_3_acacio.data.domain.Pokemon
import com.example.projeto_3_acacio.data.local.PokemonDao
import com.example.projeto_3_acacio.data.local.asDomainModel
import com.example.projeto_3_acacio.data.source.PokeApi
import com.example.projeto_3_acacio.data.source.SourcePokemon
import com.example.projeto_3_acacio.data.source.SourcePokemonContainer
import com.example.projeto_3_acacio.data.source.asLocalModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PokemonRepository(private val pokemonDao: PokemonDao) {

    val pokemons: LiveData<List<Pokemon>> =
        Transformations.map(pokemonDao.getAllPokemons()) {
        it.asDomainModel()
    }

    suspend fun refreshPokemons() {
        withContext(Dispatchers.IO) {
            val pokemons = mutableListOf<SourcePokemon>()

            for (i in 1..10) {
                pokemons.add(PokeApi.retrofitService.getPokemon(i.toString())[0])
            }

            val pokemonsContainer = SourcePokemonContainer(pokemons)

            pokemonDao.insertAllPokemon(pokemonsContainer.asLocalModel())
        }
    }
}