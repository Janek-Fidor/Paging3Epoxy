package com.example.data.feature.pokemon

import com.example.data.feature.pokemon.models.NetworkPokemon

interface PokemonRemoteDataSource {
    suspend fun getPokemonById(id: Int): Result<NetworkPokemon>
}