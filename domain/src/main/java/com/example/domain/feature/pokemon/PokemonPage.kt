package com.example.domain.feature.pokemon

data class PokemonPage(
    val prev: String?,
    val next: String?,
    val results: List<PokemonSnapshot>
)