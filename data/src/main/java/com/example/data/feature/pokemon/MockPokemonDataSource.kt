package com.example.data.feature.pokemon

import com.example.data.core.ApiResult
import com.example.data.feature.pokemon.models.NetworkPokemon
import com.example.data.feature.pokemon.models.NetworkPokemonPage
import com.example.data.feature.pokemon.models.NetworkPokemonSnapshot
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

private const val POKEMONS_PER_PAGE = 20
private const val STARTING_KEY = 1

class MockPokemonDataSource() : PokemonRemoteDataSource {
    override suspend fun getPokemonById(id: Int): ApiResult<NetworkPokemon> {
        return withContext(Dispatchers.IO) {
            delay(2_000L)
            ApiResult.Success(NetworkPokemon(id, "", "", "", emptyList()))
        }
    }

    override suspend fun getPokemonPageById(id: Int): ApiResult<NetworkPokemonPage> {
        return withContext(Dispatchers.IO) {
            delay(2_000L)
            val pokemons = List(POKEMONS_PER_PAGE) { id ->
                NetworkPokemonSnapshot("Pokemon :$id", "")
            }
            val prev = if (id == STARTING_KEY) "${null}/" else "${id - 1}/"

            ApiResult.Success(NetworkPokemonPage(prev, "${id + 1}/", pokemons))
        }
    }
}