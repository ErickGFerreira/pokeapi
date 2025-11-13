package com.pokedex.inject

import com.pokedex.data.api.PokedexApi
import com.pokedex.data.repository.PokedexRepositoryImpl
import com.pokedex.domain.repository.PokedexRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {
    @Provides
    fun providePokedexRepository(api: PokedexApi): PokedexRepository =
        PokedexRepositoryImpl(api = api)
}