package com.example.data.feature.pokemon

import com.example.data.core.ApiResult
import com.example.data.feature.pokemon.models.NetworkPokemon
import com.example.data.feature.pokemon.models.NetworkPokemonPage

interface PokemonRemoteDataSource {
    suspend fun getPokemonById(id: Int): ApiResult<NetworkPokemon>

    suspend fun getPokemonPageById(id: Int, pageSize: Int): ApiResult<NetworkPokemonPage>
}