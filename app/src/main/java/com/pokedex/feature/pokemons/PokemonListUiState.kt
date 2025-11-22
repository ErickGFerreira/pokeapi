package com.pokedex.feature.pokemons

import com.pokedex.domain.model.Pokemon
import com.pokedex.utils.error.Error
import com.pokedex.utils.state.ScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class PokemonListUiState @Inject constructor() {
    val screenState = MutableStateFlow<ScreenState>(value = ScreenState.ScreenProgress)
    val screenPresentation = MutableStateFlow<Presentation>(Presentation())
    fun showScreen(
        pokemonList: List<Pokemon>,
    ) {
        setupPaginating(isPaginating = false)
        screenState.value = ScreenState.ScreenContent
        setUpPokemonList(pokemonList = pokemonList)
    }

    fun showLoading() {
        screenState.value = ScreenState.ScreenProgress
    }

    fun showError(error: Error) {
        screenState.value = ScreenState.ScreenError(error)
    }

    fun showPaginationError(error: Error) {
        screenPresentation.update {
            it.copy(errorPagination = true, isPaginating = false, error = error)
        }
    }

    fun hidePaginationError() {
        screenPresentation.update {
            it.copy(errorPagination = false)
        }
    }

    fun setupPaginating(isPaginating: Boolean) {
        screenPresentation.update {
            it.copy(isPaginating = isPaginating)
        }
    }

    private fun setUpPokemonList(
        pokemonList: List<Pokemon>
    ) {
        screenPresentation.update {
            it.copy(
                pokemons = screenPresentation.value.pokemons + pokemonList,
                errorPagination = false
            )
        }
    }

    data class Presentation(
        val pokemons: List<Pokemon> = emptyList(),
        val isPaginating: Boolean = false,
        val errorPagination: Boolean = false,
        val error: Error? = null
    )
}