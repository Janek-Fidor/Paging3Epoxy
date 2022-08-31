package com.example.data.feature.pokemon.network

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.data.core.ApiResult
import com.example.data.feature.pokemon.models.NetworkPokemon
import com.example.data.feature.pokemon.models.NetworkPokemonSnapshot
import com.example.domain.feature.pokemon.models.Pokemon
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

private const val STARTING_KEY = 1

class PokemonPagingSource(
    private val dataSource: PokemonRemoteDataSource
) : PagingSource<Int, Pokemon>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pokemon> {
        val id = params.key ?: STARTING_KEY

        val offset = (id - 1) * params.loadSize
        val apiResult = dataSource.getPokemonPageById(params.loadSize, offset)

        return when (apiResult) {
            is ApiResult.Success -> LoadResult.Page(
                data = getPokemonInfo(apiResult.value.results).map { it.mapToDomain() },
                prevKey = when (id) {
                    STARTING_KEY -> null
                    else -> id - 1
                },
                nextKey = if (apiResult.value.next == null) null else id + 1
            )
            is ApiResult.Error -> {
                Log.e("paging exception", apiResult.exception.toString())
                LoadResult.Error(apiResult.exception)
            }
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Pokemon>): Int? =
        state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }

    private suspend fun getPokemonInfo(
        pokemonSnapshots: List<NetworkPokemonSnapshot>
    ): List<NetworkPokemon> =
        coroutineScope {
            pokemonSnapshots
                .map { async { dataSource.getPokemonByName(it.name) } }
                .awaitAll()
                .mapNotNull {
                    when (it) {
                        is ApiResult.Success -> it.value
                        is ApiResult.Error -> {
                            Log.d("pokemon_info", it.exception.toString())
                            null
                        }
                    }
                }
        }
}