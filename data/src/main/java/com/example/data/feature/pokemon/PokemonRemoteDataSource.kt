package com.example.data.feature.pokemon

import com.example.data.core.ApiResult
import com.example.data.feature.pokemon.models.NetworkPokemon
import com.example.data.feature.pokemon.models.NetworkPokemonPage

interface PokemonRemoteDataSource {
    suspend fun getPokemonByName(name: String): ApiResult<NetworkPokemon>

    suspend fun getPokemonPageById(limit: Int, offset: Int): ApiResult<NetworkPokemonPage>
}