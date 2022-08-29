package com.example.data.feature.pokemon.models

import com.example.domain.feature.pokemon.models.PokemonPage

data class NetworkPokemonPage(
    val prev: String?,
    val next: String?,
    val results: List<NetworkPokemonSnapshot>
) {
    fun mapToDomain() = PokemonPage(prev, next, results.map(NetworkPokemonSnapshot::mapToDomain))
}