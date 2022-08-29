package com.example.paging3epoxy

import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging3.PagingDataEpoxyController
import com.example.domain.feature.pokemon.models.PokemonSnapshot
import com.example.paging3epoxy.common.ViewBindingKotlinModel
import com.example.paging3epoxy.databinding.ModelPokemonListitemBinding

class PokemonListEpoxyController : PagingDataEpoxyController<PokemonSnapshot>() {
    override fun buildItemModel(currentPosition: Int, item: PokemonSnapshot?): EpoxyModel<*> =
        PokemonListItemEpoxyModel(
            pokemonSnapshot = item ?: PokemonSnapshot("error", "-1/")
        ).id("pokemon_${item?.id ?: -1}")


    data class PokemonListItemEpoxyModel(
        val pokemonSnapshot: PokemonSnapshot
    ) : ViewBindingKotlinModel<ModelPokemonListitemBinding>(R.layout.model_pokemon_listitem) {

        override fun ModelPokemonListitemBinding.bind() {
            nameText.text = pokemonSnapshot.name
        }
    }
}