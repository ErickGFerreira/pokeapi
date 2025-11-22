package com.pokedex.feature.pokemon

import com.pokedex.domain.model.Pokemon
import com.pokedex.feature.pokemons.PokemonListUiState
import com.pokedex.utils.error.Error
import com.pokedex.utils.state.ScreenState
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test

class PokemonListUiStateTest {
    private lateinit var uiState: PokemonListUiState

    @Before
    fun setup() {
        uiState = PokemonListUiState()
    }

    @Test
    fun `given pokemon list, when showScreen, then show content with correct data`() {

        // when
        uiState.showScreen(
            pokemonList = Pokemon.mockList()
        )
        // then
        assertThat(uiState.screenPresentation.value).isEqualTo(expectedPresentation)
        assertThat(uiState.screenState.value).isEqualTo(ScreenState.ScreenContent)
    }

    @Test
    fun `given paginating equals true, when load new pokemons, then show paginating progress on screen content`() {

        // when
        uiState.setupPaginating(isPaginating = true)
        // then
        assertThat(uiState.screenPresentation.value).isEqualTo(
            expectedPresentation.copy(
                isPaginating = true
            )
        )
        assertThat(uiState.screenState.value).isEqualTo(ScreenState.ScreenContent)
    }

    @Test
    fun `given ERROR_MOCK, when showError, then update screenState to ScreenError and show error`() {
        // when
        uiState.showError(error = ERROR_MOCK)
        // then
        assertThat(uiState.screenState.value).isEqualTo(ScreenState.ScreenError(error = ERROR_MOCK))
    }

    @Test
    fun `given showProgress, then change screenState to ScreenProgress`() {
        // when
        uiState.showLoading()
        // then
        assertThat(uiState.screenState.value).isEqualTo(ScreenState.ScreenProgress)
    }
    // Helpers

    private companion object {
        val ERROR_MOCK = Error(message = "erro", code = "1", title = "error title")
        val expectedPresentation = PokemonListUiState.Presentation(
            pokemons = Pokemon.mockList(),
            isPaginating = false
        )

    }
}
