package com.example.data.feature.pokemon.models

import com.example.domain.feature.pokemon.models.Pokemon
import kotlinx.serialization.Serializable

@Serializable
data class NetworkPokemon(
    val id: Int,
    val name: String,
    val sprites: NetworkPokemonSprites,
    val types: List<NetworkPokemonTypeSlot>,
    val abilities: List<NetworkPokemonAbilitySlot>
) {
    fun mapToDomain() =
        Pokemon(
            id,
            name,
            sprites.spriteUrl,
            types.map { it.type.name },
            abilities.map { it.ability.name }
        )
}