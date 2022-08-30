package com.example.data.feature.pokemon

import com.example.data.feature.pokemon.models.NetworkPokemon
import com.example.data.feature.pokemon.models.NetworkPokemonPage
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApiService {
    @GET("pokemon")
    suspend fun getPokemonPageById(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): NetworkPokemonPage

    @GET("pokemon/{name}")
    suspend fun getPokemonByName(
        @Path("name") name: String,
    ): NetworkPokemon
}