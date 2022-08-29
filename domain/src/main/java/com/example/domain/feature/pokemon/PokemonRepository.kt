package com.example.domain.feature.pokemon

import com.example.domain.core.Result
import com.example.domain.feature.pokemon.models.PokemonPage

interface PokemonRepository {
    suspend fun getPokemonPageById(id: Int): Result<PokemonPage>
}