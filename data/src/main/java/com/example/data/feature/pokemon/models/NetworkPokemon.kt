package com.example.data.feature.pokemon.models

data class NetworkPokemon(
    val id: Int,
    val name: String,
    val pictureUrl: String,
    val type: String,
    val abilities: List<NetworkPokemonAbility>
)