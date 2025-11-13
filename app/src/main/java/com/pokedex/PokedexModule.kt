package com.pokedex

import android.app.Application
import com.pokedex.inject.AppModule
import com.pokedex.inject.DaggerPokedexComponent
import com.pokedex.inject.PokedexComponent

object PokedexModule {
    lateinit var component: PokedexComponent

    fun init(
        application: Application,
    ) {
        component = DaggerPokedexComponent.builder()
            .appModule(
                AppModule(application = application)
            )
            .build()
    }
}