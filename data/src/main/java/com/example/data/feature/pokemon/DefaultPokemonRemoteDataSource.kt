package com.example.data.feature.pokemon

import com.example.data.core.ApiResult
import com.example.data.feature.pokemon.models.NetworkPokemon
import com.example.data.feature.pokemon.models.NetworkPokemonPage
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

@OptIn(ExperimentalSerializationApi::class)
class DefaultPokemonRemoteDataSource : PokemonRemoteDataSource {
    private val url = "https://pokeapi.co/api/v2/"

    private val interceptor = HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BODY)
    }
    private val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

    private val contentType: MediaType = "application/json".toMediaType()
    private val json = Json { ignoreUnknownKeys = true }

    private val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(json.asConverterFactory(contentType))
        .client(client)
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