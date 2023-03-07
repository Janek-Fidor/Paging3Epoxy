package com.example.data.feature.pokemon

import com.example.data.feature.pokemon.network.DefaultPokemonRemoteDataSource
import com.example.data.feature.pokemon.network.DefaultPokemonRepository
import com.example.domain.feature.pokemon.PokemonRepository
import org.koin.dsl.module


val dataModule = module {
    single<PokemonRepository> {
        DefaultPokemonRepository(DefaultPokemonRemoteDataSource())
    }
}