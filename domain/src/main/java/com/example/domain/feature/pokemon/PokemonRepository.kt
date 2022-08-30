package com.example.domain.feature.pokemon

import androidx.paging.PagingData
import com.example.domain.feature.pokemon.models.Pokemon
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    fun fetchPokemonPages(): Flow<PagingData<Pokemon>>
}