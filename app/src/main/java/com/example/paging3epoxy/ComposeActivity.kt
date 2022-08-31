package com.example.paging3epoxy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.core.view.setPadding
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import androidx.recyclerview.widget.LinearLayoutManager
import coil.compose.AsyncImage
import com.airbnb.epoxy.EpoxyRecyclerView
import com.example.domain.feature.pokemon.models.Pokemon
import com.example.paging3epoxy.ui.theme.Paging3EpoxyTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Paging3EpoxyTheme {
                PokemonListScreen()
            }
        }
    }
}

@Composable
fun PokemonListScreen(
    viewModel: PokemonViewModel = hiltViewModel()
) {
//    val pokemonPagingData = viewModel.pokemonFlow
//    PokemonListEpoxy(pokemonPagingData)

    val pokemonPagingItems = viewModel.pokemonFlow.collectAsLazyPagingItems()
    PokemonListCompose(pokemonPagingItems)
}

@Composable
fun PokemonListCompose(pokemonPagingItems: LazyPagingItems<Pokemon>) {
    LazyColumn {
        items(pokemonPagingItems, { item: Pokemon -> item.id })
        { pokemon ->
            pokemon?.let { PokemonEntry(pokemon) }
        }
    }
}

@Composable
fun PokemonEntry(pokemon: Pokemon) {
    ConstraintLayout(
        Modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        val (details, image) = createRefs()

        val pokemonDetails = PokemonDetailsUIState(
            name = pokemon.name,
            types = pokemon.types.joinToString(" "),
            abilities = pokemon.abilities.joinToString(" ")
        )

        PokemonDetails(
            pokemonDetails,
            modifier = Modifier.constrainAs(details) {
                start.linkTo(parent.start, margin = 16.dp)
            }
        )
        PokemonImage(
            pokemonImageUrl = pokemon.pictureUrl,
            modifier = Modifier.constrainAs(image) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                end.linkTo(parent.end)

                height = Dimension.fillToConstraints
            },
        )
    }
}

data class PokemonDetailsUIState(
    val name: String,
    val types: String,
    val abilities: String,
)

@Composable
fun PokemonDetails(pokemonDetails: PokemonDetailsUIState, modifier: Modifier = Modifier) {
    Surface(modifier = modifier) {
        Column() {
            Text(text = pokemonDetails.name, style = typography.h1)
            Text(text = pokemonDetails.types, style = typography.body1)
            Text(text = pokemonDetails.abilities, style = typography.body1)
        }
    }
}

@Composable
fun PokemonImage(pokemonImageUrl: String, modifier: Modifier = Modifier) {
    AsyncImage(
        modifier = modifier,
        model = pokemonImageUrl,
        contentDescription = "pokemon image",
        contentScale = ContentScale.FillHeight,
    )
}

@Composable
fun PokemonListEpoxy(pokemonPagingData: Flow<PagingData<Pokemon>>) {
    val coroutineScope = rememberCoroutineScope()

    AndroidView(factory = { ctx ->
        val epoxyController = PokemonListEpoxyController()
        val view = EpoxyRecyclerView(ctx).apply {
            setController(epoxyController)
            setItemSpacingDp(8)
            setPadding(8)
            layoutManager = LinearLayoutManager(ctx)
        }

        coroutineScope.launch {
            pokemonPagingData.collect {
                epoxyController.submitData(it)
            }
        }
        view
    }, modifier = Modifier.fillMaxWidth())

}