package com.pokedex.feature.pokemons

import android.app.PendingIntent
import com.pokedex.utils.event.UiEvent
import javax.inject.Inject

class PokemonsListUiEvent @Inject constructor() :
    UiEvent<PokemonsListUiEvent.Event>() {
    sealed interface Event {
        data object Finish : Event

        data object NavigateToPokemonDetail : Event

    }
}