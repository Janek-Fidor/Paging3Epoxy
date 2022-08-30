package com.example.data.feature.pokemon

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.domain.feature.pokemon.PokemonRepository
import com.example.domain.feature.pokemon.models.PokemonSnapshot
import kotlinx.coroutines.flow.Flow


private const val VISIBLE_PAGE_SIZE = 20
private const val PAGE_SIZE = VISIBLE_PAGE_SIZE * 2
private const val PREFETCH_DISTANCE = PAGE_SIZE * 2

class MockPokemonRepository(private val dataSource: PokemonRemoteDataSource) : PokemonRepository {
    override fun fetchPokemonPages(): Flow<PagingData<PokemonSnapshot>> = Pager(
        pagingSourceFactory = { PokemonPagingSource(dataSource) },
        config = PagingConfig(
            pageSize = PAGE_SIZE,
            prefetchDistance = PREFETCH_DISTANCE,
            enablePlaceholders = false,
            initialLoadSize = PAGE_SIZE
        )
    ).flow
}