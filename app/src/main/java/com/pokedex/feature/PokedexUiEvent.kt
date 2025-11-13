package com.pokedex.feature

import com.pokedex.utils.EMPTY_STRING
import com.pokedex.utils.event.UiEvent
import javax.inject.Inject

class PokedexUiEvent @Inject constructor() : UiEvent<PokedexUiEvent.Navigation>() {
    sealed class Navigation(
        val route: String = EMPTY_STRING,
    ) {
        data object PokemonList : Navigation(route = "pokedex-pokemons-list")

        data object Finish : Navigation()
    }
}