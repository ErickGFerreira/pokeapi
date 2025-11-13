package com.pokedex.inject

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pokedex.utils.viewmodel.ViewModelFactory
import com.pokedex.utils.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import com.pokedex.feature.pokemons.PokemonListViewModel

@Module
abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(PokemonListViewModel::class)
    internal abstract fun bindPokemonListViewModel(
        viewModel: PokemonListViewModel,
    ): ViewModel
}