package com.example.data.feature.pokemon

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
        MockPokemonRemoteDataSource()

    @Provides
    fun provideRepository(dataSource: PokemonRemoteDataSource): PokemonRepository =
        MockPokemonRepository(dataSource)
}