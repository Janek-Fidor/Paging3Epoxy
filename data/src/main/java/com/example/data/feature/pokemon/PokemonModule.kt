package com.example.data.feature.pokemon

import com.example.data.feature.pokemon.network.DefaultPokemonRemoteDataSource
import com.example.data.feature.pokemon.network.DefaultPokemonRepository
import com.example.data.feature.pokemon.network.PokemonRemoteDataSource
import com.example.domain.feature.pokemon.PokemonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object PokemonModule {

    @Provides
    fun provideRemoteDataSource(): PokemonRemoteDataSource =
        DefaultPokemonRemoteDataSource()

    @Provides
    fun provideRepository(dataSource: PokemonRemoteDataSource): PokemonRepository =
        DefaultPokemonRepository(dataSource)
}