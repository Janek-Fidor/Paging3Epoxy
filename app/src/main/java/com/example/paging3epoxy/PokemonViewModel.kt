package com.example.paging3epoxy

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.domain.feature.pokemon.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(
    repository: PokemonRepository
) : ViewModel() {
    val pokemonFlow = repository.fetchPokemonPages().cachedIn(viewModelScope)
}