package com.example.domain.feature.pokemon

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.domain.core.execute

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
                else -> id - 1
            },
            nextKey = if (pokemonPage.next == null) null else id + 1
        )
    }

    override fun getRefreshKey(state: PagingState<Int, PokemonSnapshot>): Int? =
        state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
}