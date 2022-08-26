package com.example.domain.feature.pokemon

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.domain.core.execute
import java.lang.Integer.max

private const val STARTING_KEY = 1

class PokemonPagingSource(
    private val dataSource: PokemonRemoteDataSource
) : PagingSource<Int, Pokemon>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pokemon> {
        val start = params.key ?: STARTING_KEY
        val pokemonIdsToFetch = start.until(start + params.loadSize)

        val pokemons = pokemonIdsToFetch.mapNotNull { id ->
            dataSource.getPokemonById(id).execute(
                onSuccess = { it.value }, onError = { null }
            )
        }

        return LoadResult.Page(
            data = pokemons,
            prevKey = when (start) {
                STARTING_KEY -> null
                else -> ensureValidKey(key = pokemonIdsToFetch.first - params.loadSize)
            },
            nextKey = pokemonIdsToFetch.last + 1
        )
    }

    override fun getRefreshKey(state: PagingState<Int, Pokemon>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val pokemon = state.closestItemToPosition(anchorPosition) ?: return null
        return ensureValidKey(key = pokemon.id - (state.config.pageSize / 2))
    }

    private fun ensureValidKey(key: Int) = max(STARTING_KEY, key)
}