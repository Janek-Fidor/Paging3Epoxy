package com.example.data.feature.pokemon.network

import com.example.data.core.ApiResult
import com.example.data.feature.pokemon.models.NetworkPokemon
import com.example.data.feature.pokemon.models.NetworkPokemonPage
import com.example.data.feature.pokemon.models.NetworkPokemonSnapshot
import com.example.data.feature.pokemon.models.NetworkPokemonSprites
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class MockPokemonRemoteDataSource : PokemonRemoteDataSource {
    override suspend fun getPokemonByName(name: String): ApiResult<NetworkPokemon> {
        return withContext(Dispatchers.IO) {
            delay(2_000L)
            ApiResult.Success(
                NetworkPokemon(
                    0,
                    "",
                    NetworkPokemonSprites(""),
                    emptyList(),
                    emptyList()
                )
            )
        }
    }

    override suspend fun getPokemonPageById(
        limit: Int,
        offset: Int
    ): ApiResult<NetworkPokemonPage> {
        return withContext(Dispatchers.IO) {
            delay(2_000L)
            val pokemons = List(limit) {
                val pokemonId = offset + 1 + it
                NetworkPokemonSnapshot("Pokemon :$pokemonId", "$pokemonId/")
            }
            val prev = if (offset == 0) "${null}/" else "${offset - limit}/"

            ApiResult.Success(NetworkPokemonPage(prev, "${offset + limit}/", pokemons))
        }
    }
}