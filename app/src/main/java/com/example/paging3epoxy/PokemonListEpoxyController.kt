package com.example.paging3epoxy

import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging3.PagingDataEpoxyController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.domain.feature.pokemon.models.Pokemon
import com.example.paging3epoxy.common.ViewBindingKotlinModel
import com.example.paging3epoxy.databinding.ModelPokemonListitemBinding

class PokemonListEpoxyController : PagingDataEpoxyController<Pokemon>() {
    override fun buildItemModel(currentPosition: Int, item: Pokemon?): EpoxyModel<*> =
        PokemonListItemEpoxyModel(
            pokemonSnapshot = item ?: Pokemon(-1, "", "", emptyList(), emptyList())
        ).id("pokemon_${item?.id ?: -1}")


    data class PokemonListItemEpoxyModel(
        val pokemonSnapshot: Pokemon
    ) : ViewBindingKotlinModel<ModelPokemonListitemBinding>(R.layout.model_pokemon_listitem) {

        override fun ModelPokemonListitemBinding.bind() {
            nameText.text = pokemonSnapshot.name
            typeText.text = pokemonSnapshot.types.joinToString(" ")
            abilitiesText.text = pokemonSnapshot.abilities.joinToString(" ")

            val options = RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher_round)

            Glide
                .with(this.root.context)
                .load(pokemonSnapshot.pictureUrl)
                .apply(options)
                .override(300, 250)
                .into(this.frontImage)
        }
    }
}