package com.example.data.feature.pokemon

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.data.core.ApiResult
import com.example.domain.feature.pokemon.models.PokemonSnapshot

private const val STARTING_KEY = 1

class PokemonPagingSource(
    private val dataSource: PokemonRemoteDataSource
) : PagingSource<Int, PokemonSnapshot>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonSnapshot> {
        val id = params.key ?: STARTING_KEY
        val apiResult = dataSource.getPokemonPageById(id, params.loadSize)
        return when (apiResult) {
            is ApiResult.Success -> LoadResult.Page(
                data = apiResult.value.mapToDomain().results,
                prevKey = when (id) {
                    STARTING_KEY -> null
                    else -> id - 1
                },
                nextKey = if (apiResult.value.next == null) null else id + 1
            )
            is ApiResult.Error -> LoadResult.Error(apiResult.exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PokemonSnapshot>): Int? =
        state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
}