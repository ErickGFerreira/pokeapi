package com.pokedex.feature

import com.pokedex.utils.viewmodel.FlowDataSavedStateHandle
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class PokedexViewModelTest {
    private val navigationEvent: PokedexUiEvent = mockk(relaxed = true)

    private lateinit var viewModel: PokedexViewModel

    private val handle: FlowDataSavedStateHandle<PokedexViewModel.FlowData> =
        mockk(relaxed = true)

    @Before
    fun setup() {
        viewModel =
            PokedexViewModel(
                uiEvent = navigationEvent,
                handle = handle,
            )
    }

    @Test
    fun `given PokemonList, when navigate, then navigate to pokemon list`() {
        // when
        viewModel.navigate(navigation = PokedexUiEvent.Navigation.PokemonList)
        // then
        verify(exactly = 1) { navigationEvent.send(event = PokedexUiEvent.Navigation.PokemonList) }
    }

    @Test
    fun `given Finish, when navigate, then Finish`() {
        // when
        viewModel.navigate(navigation = PokedexUiEvent.Navigation.Finish)
        // then
        verify(exactly = 1) { navigationEvent.send(event = PokedexUiEvent.Navigation.Finish) }
    }
}