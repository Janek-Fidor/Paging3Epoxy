package com.example.paging3epoxy

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.domain.feature.pokemon.PokemonRepository
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class PokemonViewModel(
    repository: PokemonRepository
) : ViewModel() {
    val pokemonFlow = repository.fetchPokemonPages().cachedIn(viewModelScope)
}