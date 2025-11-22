package com.pokedex.inject

import com.pokedex.domain.repository.PokedexRepository
import com.pokedex.domain.usecase.GetPokemonDetailUseCase
import com.pokedex.domain.usecase.GetPokemonListUseCase
import com.pokedex.utils.safe.safeRunDispatcher
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    fun provideGetPokemonListUseCase(repository: PokedexRepository) =
        GetPokemonListUseCase { currentPage ->
            safeRunDispatcher {
                repository.getPokemonList(currentPage = currentPage)
            }
        }

    @Provides
    fun providesGetPokemonDetailUseCase(repository: PokedexRepository) =
        GetPokemonDetailUseCase { url ->
            safeRunDispatcher {
                repository.getPokemonDetail(url = url)
            }
        }
}