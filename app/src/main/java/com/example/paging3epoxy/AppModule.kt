package com.example.paging3epoxy

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {
    viewModel {
        PokemonViewModel(get())
    }
}