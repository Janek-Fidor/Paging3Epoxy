package com.example.paging3epoxy

import androidx.lifecycle.ViewModel
import com.example.data.feature.pokemon.MockPokemonRemoteDataSource
import com.example.data.feature.pokemon.MockPokemonRepository

class PokemonViewModel : ViewModel() {

    private val repository = MockPokemonRepository(MockPokemonRemoteDataSource())
    val pokemonFlow = repository.fetchPokemonPages()
}