package com.example.domain.feature.pokemon

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.domain.core.execute
import java.lang.Integer.max

private const val STARTING_KEY = 1

class PokemonPagingSource(
    private val repo: PokemonRepository
) : PagingSource<Int, PokemonSnapshot>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonSnapshot> {
        val id = params.key ?: STARTING_KEY

        val pokemonPage = repo.getPokemonPageById(id).execute(
            onSuccess = { it.value },
            onError = { return LoadResult.Error(it.exception) }
        )


        return LoadResult.Page(
            data = pokemonPage.results,
            prevKey = when (id) {
                STARTING_KEY -> null
                else -> ensureValidKey(key = id - 1)
            },
            nextKey = id + 1
        )
    }

    override fun getRefreshKey(state: PagingState<Int, PokemonSnapshot>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val pokemon = state.closestItemToPosition(anchorPosition) ?: return null
        return ensureValidKey(key = pokemon.id - (state.config.pageSize / 2))
    }

    private fun ensureValidKey(key: Int) = max(STARTING_KEY, key)
}