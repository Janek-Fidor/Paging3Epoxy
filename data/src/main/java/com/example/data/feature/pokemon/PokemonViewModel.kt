package com.example.data.feature.pokemon

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.domain.feature.pokemon.PokemonPagingSource

class PokemonViewModel : ViewModel() {

    private val repository = MockPokemonRepository(MockPokemonDataSource())
    val flow = Pager(
        PagingConfig(
            pageSize = 20 * 2,
            prefetchDistance = 20 * 4,
            enablePlaceholders = false
        )
    ) {
        PokemonPagingSource(repository)
    }.flow
}