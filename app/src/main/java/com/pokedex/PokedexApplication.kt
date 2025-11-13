package com.pokedex

import android.app.Application

class PokedexApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        PokedexModule.init(application = this)
        PokedexModule.component.inject(application = this)
    }

}
