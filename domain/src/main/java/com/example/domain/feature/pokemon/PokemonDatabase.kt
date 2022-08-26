package com.example.domain.feature.pokemon

import com.example.domain.core.ApiResult

interface PokemonDatabase {
    suspend fun add(pokemon: Pokemon): ApiResult<Unit>

    suspend fun getById(id: Int): ApiResult<Pokemon?>
}