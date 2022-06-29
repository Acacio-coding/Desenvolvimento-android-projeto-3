package com.example.projeto_3_acacio.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface PokemonDao {

    @Query("SELECT * FROM tb_pokemon")
    fun getAllPokemons(): LiveData<List<LocalPokemon>>

    @Query("SELECT * FROM tb_pokemon WHERE id = :id")
    fun getPokemon(id: Int): LiveData<LocalPokemon>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllPokemon(pokemon: List<LocalPokemon>)
}