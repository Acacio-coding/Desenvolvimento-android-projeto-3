package com.example.projeto_3_acacio.data.source

import com.example.projeto_3_acacio.data.domain.Pokemon
import com.example.projeto_3_acacio.data.local.LocalPokemon

data class SourcePokemonContainer(
    val sourcePokemon: List<SourcePokemon>
)

data class SourcePokemon (
    val number: String,
    val name: String,
    val types: List<String>,
    val gen: Int,
    val description: String,
    val sprite: String,
)

fun SourcePokemonContainer.asDomainModel(): List<Pokemon> {
    return sourcePokemon.map {
        Pokemon (
            number = it.number,
            name = it.name,
            types = it.types,
            gen = it.gen,
            description = it.description,
            sprite = it.sprite
        )
    }
}

fun SourcePokemonContainer.asLocalModel(): List<LocalPokemon> {
    return sourcePokemon.map {
        LocalPokemon (
            id = it.number.toInt(),
            name = it.name,
            types = it.types,
            gen = it.gen,
            description = it.description,
            sprite = it.sprite
        )
    }
}
