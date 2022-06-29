package com.example.projeto_3.views

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.projeto_3_acacio.data.domain.Pokemon
import com.example.projeto_3_acacio.data.repository.PokemonRepository
import kotlinx.coroutines.launch
import java.io.IOException

class PokemonViewModel(private val repository: PokemonRepository): ViewModel() {

    init {
        if (repository.pokemons.value.isNullOrEmpty()) {
            refreshPokemonsFromRepository()
        }
    }

    val pokemons = repository.pokemons

    private val _eventNetworkError = MutableLiveData("")

    private fun refreshPokemonsFromRepository() {
        viewModelScope.launch {
            try {
                repository.refreshPokemons()
                _eventNetworkError.value = "Success"
            } catch (networkError: IOException) {
                Log.d("Network Error", "${networkError.message}")
                _eventNetworkError.value = networkError.message
            }
        }
    }

    fun getPokemon(id: Int): Pokemon {
        val list = pokemons.value?.toList() ?: listOf()

        return list.find {
            it.number == id.toString()
        } ?: Pokemon("", -1, "", "", "", listOf())
    }
}

class PokemonVMFactory(private val repository: PokemonRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PokemonViewModel::class.java)) {
            return PokemonViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}