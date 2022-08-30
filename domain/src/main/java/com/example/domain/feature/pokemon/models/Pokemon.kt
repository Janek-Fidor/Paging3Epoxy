package com.example.domain.feature.pokemon.models

data class Pokemon(
    val id: Int,
    val name: String,
    val pictureUrl: String,
    val types: List<String>,
    val abilities: List<String>
)