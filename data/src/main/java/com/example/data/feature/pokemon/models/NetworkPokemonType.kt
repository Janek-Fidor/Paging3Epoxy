package com.example.data.feature.pokemon.models

import kotlinx.serialization.Serializable

@Serializable
data class NetworkPokemonType(
    val name: String
)


@Serializable
data class NetworkPokemonTypeSlot(
    val type: NetworkPokemonType
)
