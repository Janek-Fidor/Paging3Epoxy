package com.example.domain.feature.pokemon.models

data class PokemonPage(
    val prev: String?,
    val next: String?,
    val results: List<PokemonSnapshot>
)