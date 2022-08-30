package com.example.data.feature.pokemon.models

import com.example.domain.feature.pokemon.models.PokemonPage
import kotlinx.serialization.Serializable

@Serializable
data class NetworkPokemonPage(
    val previous: String?,
    val next: String?,
    val results: List<NetworkPokemonSnapshot>
) {
    fun mapToDomain() = PokemonPage(previous, next, results.map(NetworkPokemonSnapshot::mapToDomain))
}