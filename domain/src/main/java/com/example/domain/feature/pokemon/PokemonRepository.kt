package com.example.domain.feature.pokemon

import androidx.paging.PagingData
import com.example.domain.feature.pokemon.models.PokemonSnapshot
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    fun fetchPokemonPages(): Flow<PagingData<PokemonSnapshot>>
}