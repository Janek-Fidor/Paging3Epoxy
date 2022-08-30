package com.example.data.feature.pokemon.models

import kotlinx.serialization.Serializable

@Serializable
data class NetworkPokemonAbility(
    val id: Int,
    val name: String
)