package com.example.data.feature.pokemon.models

import kotlinx.serialization.Serializable

@Serializable
data class NetworkPokemonPage(
    val previous: String?,
    val next: String?,
    val results: List<NetworkPokemonSnapshot>
)