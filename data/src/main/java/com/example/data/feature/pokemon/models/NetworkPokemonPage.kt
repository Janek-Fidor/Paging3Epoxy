package com.example.data.feature.pokemon.models

data class NetworkPokemonPage (
    val prev: String?,
    val next: String?,
    val results: List<NetworkPokemonSnapshot>
        )