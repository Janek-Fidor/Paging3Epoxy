package com.example.data.feature.pokemon.models

import kotlinx.serialization.Serializable

@Serializable
data class NetworkPokemonAbility(
    val name: String
)

@Serializable
data class NetworkPokemonAbilitySlot(
    val ability: NetworkPokemonAbility
)