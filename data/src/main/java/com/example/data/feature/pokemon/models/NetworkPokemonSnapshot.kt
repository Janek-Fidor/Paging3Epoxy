package com.example.data.feature.pokemon.models

import com.example.domain.feature.pokemon.models.PokemonSnapshot
import kotlinx.serialization.Serializable

@Serializable
data class NetworkPokemonSnapshot(
    val name: String,
    val url: String
) {
    val id: Int? get() = url.dropLast(1).split("/").last().toIntOrNull()

    fun mapToDomain() = PokemonSnapshot(name, url)
}