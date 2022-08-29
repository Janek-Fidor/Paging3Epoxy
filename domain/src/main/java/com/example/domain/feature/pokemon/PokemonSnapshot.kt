package com.example.domain.feature.pokemon

data class PokemonSnapshot(
    val name: String,
    val url: String
) {
    val id: Int? get() = url.dropLast(1).split("/").last().toIntOrNull()
}