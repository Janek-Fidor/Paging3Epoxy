package com.example.data.feature.pokemon

import com.example.data.core.ApiResult
import com.example.data.feature.pokemon.models.NetworkPokemon
import com.example.data.feature.pokemon.models.NetworkPokemonPage
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit

@OptIn(ExperimentalSerializationApi::class)
class DefaultPokemonRemoteDataSource : PokemonRemoteDataSource {
    private val contentType: MediaType = MediaType.get("application/json")
    private val url = "https://pokeapi.co/api/v2/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(Json.asConverterFactory(contentType))
        .build()

    private val pokemonService by lazy { retrofit.create(PokemonApiService::class.java) }

    override suspend fun getPokemonById(id: Int): ApiResult<NetworkPokemon> =
        safeApiCall { pokemonService.getPokemonById(id) }

    override suspend fun getPokemonPageById(limit: Int, offset: Int): ApiResult<NetworkPokemonPage> =
        safeApiCall { pokemonService.getPokemonPageById(limit, offset) }

    private suspend fun <T> safeApiCall(apiCall: suspend () -> T): ApiResult<T> =
        try {
            ApiResult.Success(apiCall())
        } catch (e: Exception) {
            ApiResult.Error(e)
        }
}