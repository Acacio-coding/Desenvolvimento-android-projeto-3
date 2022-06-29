package com.example.projeto_3_acacio.data.local

import android.content.Context
import androidx.room.*

@Database(entities = [LocalPokemon::class], version = 1)
@TypeConverters(ListConverter::class)
abstract class PokemonDatabase : RoomDatabase() {

    abstract fun pokemonDao(): PokemonDao

    companion object {

        @Volatile
        private var INSTANCE: PokemonDatabase? = null

        fun getInstance(context: Context): PokemonDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PokemonDatabase::class.java,
                    "pokemon_database"
                ).fallbackToDestructiveMigration().build()

                INSTANCE = instance
                return instance
            }
        }
    }
}