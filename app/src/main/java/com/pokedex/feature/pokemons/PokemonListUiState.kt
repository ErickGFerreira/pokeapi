package com.pokedex.feature.pokemons

import com.pokedex.domain.model.Pokemon
import com.pokedex.utils.state.ScreenState
import com.pokedex.utils.error.Error
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class PokemonListUiState @Inject constructor() {
    val screenState = MutableStateFlow<ScreenState>(value = ScreenState.ScreenProgress)
    val cardsPresentation = MutableStateFlow<List<CardPresentation>>(value = emptyList())


    fun showScreen(
        pokemonList: List<Pokemon>,
    ) {
        setUpPokemonList(pokemonList = pokemonList)
        showContent()
    }

    fun showError(error: Error) {
        screenState.value = ScreenState.ScreenError(error = error)
    }

    fun showProgress() {
        screenState.value = ScreenState.ScreenProgress
    }

    private fun showContent() {
        screenState.value = ScreenState.ScreenContent
    }

    private fun Pokemon.toCardPresentation() = CardPresentation(
        name = name,
        imageUrl = imageUrl,
        type = type,
    )

    private fun setUpPokemonList(
        pokemonList: List<Pokemon>
    ) {
        cardsPresentation.value = pokemonList.map { it.toCardPresentation() }
    }

    data class CardPresentation(
        val name: String,
        val type: String,
        val imageUrl: String,
    )
}