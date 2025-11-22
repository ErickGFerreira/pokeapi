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

    private var currentPage = 0

    fun setup(flowData: PokedexViewModel.FlowData) {
        getPokemonList()
    }

    fun onActionEvent(action: PokemonListScreenAction) =
        action.fold(
            closeButtonAction = ::finish,
            errorCloseButtonAction = ::finish,
            errorButtonAction = { getPokemonList() },
            errorPaginationButtonAction = {
                uiState.hidePaginationError()
                loadMorePokemons(page = currentPage)
            },
            goToPokemonDetailAction = {},
            paginateAction = {
                currentPage++
                loadMorePokemons(page = currentPage)
            },
            onToastDismissedAction = {
                uiState.hidePaginationError()
            }
        )

    @VisibleForTesting
    fun getPokemonList() {
        viewModelScope.launch {
            uiState.showLoading()
            pokemonUseCase.execute().fold(
                onSuccess = { pokemonList ->
                    uiState.showScreen(pokemonList = pokemonList)
                },
                onFailure = uiState::showError
            )
        }
    }

    @VisibleForTesting
    fun loadMorePokemons(page: Int) {
        viewModelScope.launch {
            uiState.setupPaginating(isPaginating = true)
            pokemonUseCase.execute(currentPage = page).fold(
                onSuccess = { pokemonList ->
                    uiState.showScreen(pokemonList = pokemonList)
                },
                onFailure = uiState::showPaginationError
            )
        }
    }

    private fun finish() {
        uiEvent.send(event = PokemonsListUiEvent.Event.Finish)
    }

}
