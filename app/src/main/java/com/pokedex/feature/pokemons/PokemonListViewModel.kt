package com.pokedex.feature.pokemons

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pokedex.domain.usecase.PokemonUseCase
import com.pokedex.feature.PokedexViewModel
import com.pokedex.utils.safe.fold
import kotlinx.coroutines.launch
import javax.inject.Inject

class PokemonListViewModel @Inject constructor(
    private val pokemonUseCase: PokemonUseCase,
    val uiState: PokemonListUiState,
    val uiEvent: PokemonsListUiEvent,
) : ViewModel() {

    var flowData = PokedexViewModel.FlowData()

        fun setup(flowData: PokedexViewModel.FlowData) {
        this.flowData = flowData
        getPokemonList()
    }

    fun onActionEvent(action: PokemonListScreenAction) =
        action.fold(
            closeButtonAction = ::finish,
            errorCloseButtonAction = ::finish,
            errorButtonAction = { getPokemonList() },
            goToPokemonDetailAction = {},
        )

    @VisibleForTesting
    fun getPokemonList() {
        viewModelScope.launch {

            uiState.showProgress()
            pokemonUseCase.execute().fold(
                onSuccess = { pokemonList ->
                    uiState.showScreen(pokemonList = pokemonList)
                },
                onFailure = uiState::showError,
            )
        }
    }

    private fun finish() {
        uiEvent.send(event = PokemonsListUiEvent.Event.Finish)
    }

}
