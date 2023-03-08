package com.example.data.feature.pokemon.network

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.domain.feature.pokemon.PokemonRepository
import com.example.domain.feature.pokemon.models.Pokemon
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Named
import org.koin.core.annotation.Single

private const val VISIBLE_PAGE_SIZE = 20
private const val PAGE_SIZE = VISIBLE_PAGE_SIZE * 2
private const val PREFETCH_DISTANCE = PAGE_SIZE * 2

@Single
@Named("DefaultRepo")
class DefaultPokemonRepository(
    private val dataSource: PokemonRemoteDataSource
) : PokemonRepository {
    override fun fetchPokemonPages(): Flow<PagingData<Pokemon>> = Pager(
        pagingSourceFactory = { PokemonPagingSource(dataSource) },
        config = PagingConfig(
            pageSize = PAGE_SIZE,
            prefetchDistance = PREFETCH_DISTANCE,
            enablePlaceholders = false,
            initialLoadSize = PAGE_SIZE
        )
    ).flow
}