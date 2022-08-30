package com.example.data.feature.pokemon.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkPokemonSprites(
    @SerialName("front_default") val spriteUrl: String
)