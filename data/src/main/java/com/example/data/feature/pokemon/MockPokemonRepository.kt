package com.example.data.feature.pokemon

import com.example.data.core.ApiResult
import com.example.domain.core.Result
import com.example.domain.feature.pokemon.PokemonRepository
import com.example.domain.feature.pokemon.models.PokemonPage

class MockPokemonRepository(private val dataSource: MockPokemonDataSource) : PokemonRepository {
    override suspend fun getPokemonPageById(id: Int): Result<PokemonPage> =
        when (val apiResult = dataSource.getPokemonPageById(id)) {
            is ApiResult.Success -> Result.Success(apiResult.value.mapToDomain())
            is ApiResult.Error -> Result.Error(apiResult.exception)
        }
}