package com.example.domain.feature.pokemon

data class Pokemon(
    val id: Int,
    val name: String,
    val pictureUrl: String,
    val type: PokemonType,
    val abilities: List<PokemonAbility>
)