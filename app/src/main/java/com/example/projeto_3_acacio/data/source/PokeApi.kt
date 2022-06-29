package com.example.projeto_3_acacio.data.source

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL = "https://pokeapi.glitch.me/v1/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface PokeApiService {
    @GET("pokemon/{pokemon_id}")
    suspend fun getPokemon(@Path("pokemon_id") pokemon_id: String): List<SourcePokemon>
}

object PokeApi {
    val retrofitService: PokeApiService by lazy {
        retrofit.create(PokeApiService::class.java)
    }
}
