package com.example.data.feature.pokemon.models

data class NetworkPokemonSnapshot(
    val name: String,
    val url: String
) {
    val id: Int? get() = url.dropLast(1).split("/").last().toIntOrNull()
}