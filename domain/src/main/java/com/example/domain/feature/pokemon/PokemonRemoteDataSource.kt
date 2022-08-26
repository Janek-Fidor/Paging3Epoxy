package com.example.domain.feature.pokemon

import com.example.domain.core.ApiResult

interface PokemonRemoteDataSource {
    suspend fun getPokemonById(id: Int): ApiResult<Pokemon>

}