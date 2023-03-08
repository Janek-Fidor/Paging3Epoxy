package com.example.paging3epoxy

import android.app.Application
import com.example.data.feature.pokemon.dataModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PokemonApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@PokemonApp)
            modules(
                appModule,
                dataModule
            )
        }
    }
}