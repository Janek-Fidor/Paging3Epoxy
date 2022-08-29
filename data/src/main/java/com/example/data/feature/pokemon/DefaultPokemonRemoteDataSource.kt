package com.example.data.feature.pokemon

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit

@OptIn(ExperimentalSerializationApi::class)
class DefaultPokemonRemoteDataSource() {
    private val contentType: MediaType = MediaType.get("application/json")
    private val url = "https://pokeapi.co/api/v2/"

    val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(Json.asConverterFactory(contentType))
        .build()
}