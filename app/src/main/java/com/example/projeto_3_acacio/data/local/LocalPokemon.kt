package com.example.projeto_3_acacio.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.example.projeto_3_acacio.data.domain.Pokemon
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Entity(
    tableName = "tb_pokemon"
)
data class LocalPokemon(
    @PrimaryKey
    val id: Int,
    val name: String,
    val types: List<String>,
    val gen: Int,
    val description: String,
    val sprite: String,
)

class ListConverter {
    @TypeConverter
    fun fromString(value: String): List<String> {
        val type = object : TypeToken<List<String>>(){}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun fromList(value: List<String>): String {
        return Gson().toJson(value)
    }
}

fun List<LocalPokemon>.asDomainModel(): List<Pokemon> {
    return map {
        Pokemon(
            number = it.id.toString(),
            name = it.name,
            types = it.types,
            gen = it.gen,
            description = it.description,
            sprite = it.sprite
        )
    }
}