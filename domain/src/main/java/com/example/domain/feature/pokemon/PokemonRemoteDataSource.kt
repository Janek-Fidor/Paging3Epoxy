package com.example.domain.feature.pokemon

import androidx.paging.PagingData
import com.example.domain.core.ApiResult
import kotlinx.coroutines.flow.Flow

interface PokemonRemoteDataSource {
    suspend fun getById(id: Int): ApiResult<Pokemon>
}