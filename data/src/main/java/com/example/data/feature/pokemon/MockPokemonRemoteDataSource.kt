package com.example.data.feature.pokemon

import com.example.data.core.ApiResult
import com.example.data.feature.pokemon.models.NetworkPokemon
import com.example.data.feature.pokemon.models.NetworkPokemonPage
import com.example.data.feature.pokemon.models.NetworkPokemonSnapshot
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

private const val STARTING_KEY = 1

class MockPokemonRemoteDataSource() : PokemonRemoteDataSource {
    override suspend fun getPokemonById(id: Int): ApiResult<NetworkPokemon> {
        return withContext(Dispatchers.IO) {
            delay(2_000L)
            ApiResult.Success(NetworkPokemon(id, "", "", "", emptyList()))
        }
    }

    override suspend fun getPokemonPageById(id: Int, pageSize: Int): ApiResult<NetworkPokemonPage> {
        return withContext(Dispatchers.IO) {
            delay(2_000L)
            val pokemons = List(pageSize) {
                val pokemonId = (id - 1) * pageSize + 1 + it
                NetworkPokemonSnapshot("Pokemon :$pokemonId", "$pokemonId/")
            }
            val prev = if (id == STARTING_KEY) "${null}/" else "${id - 1}/"

            ApiResult.Success(NetworkPokemonPage(prev, "${id + 1}/", pokemons))
        }
    }
}