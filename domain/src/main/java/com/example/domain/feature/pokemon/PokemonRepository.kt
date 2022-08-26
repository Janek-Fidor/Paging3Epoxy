package com.example.domain.feature.pokemon

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    fun getPokemons(): Flow<PagingData<Pokemon>>
}