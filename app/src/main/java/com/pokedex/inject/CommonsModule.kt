package com.pokedex.inject

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CommonsModule {

    @Provides
    @Singleton
    fun providesGson() = Gson()
}