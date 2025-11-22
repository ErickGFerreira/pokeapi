package com.pokedex.feature.pokemon

import com.pokedex.domain.model.Pokemon
import com.pokedex.domain.usecase.PokemonUseCase
import com.pokedex.feature.pokemons.PokemonListScreenAction
import com.pokedex.feature.pokemons.PokemonListUiState
import com.pokedex.feature.pokemons.PokemonListViewModel
import com.pokedex.feature.pokemons.PokemonsListUiEvent
import com.pokedex.utils.safe.Result
import com.pokedex.utils.test.CoroutinesTestRule
import io.mockk.coEvery
import io.mockk.coVerifyOrder
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.random.Random

@ExperimentalCoroutinesApi
class PokemonListViewModelTest {
    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private val pokemonUseCase: PokemonUseCase =
        mockk(relaxed = true)

    private val uiState: PokemonListUiState = mockk(relaxed = true)

    private val uiEvent: PokemonsListUiEvent = mockk(relaxed = true)

    private lateinit var viewModel: PokemonListViewModel

    private val currentPage = Random.nextInt()

    @Before
    fun setup() {
        viewModel =
            PokemonListViewModel(
                pokemonUseCase = pokemonUseCase,
                uiState = uiState,
                uiEvent = uiEvent,
            )
    }

    @Test
    fun `given getPokemonList success, when getPokemonList with first page, then show screen with data`() =
        coroutinesTestRule.runTest {
            // Given
            coEvery {
                pokemonUseCase.execute(currentPage = 0)
            } returns Result.Success(data = POKEMON_LIST.toMutableList())

            // When
            viewModel.getPokemonList()

            // Then
            coVerifyOrder {
                uiState.showLoading()
                pokemonUseCase.execute()
                uiState.showScreen(
                    pokemonList = POKEMON_LIST
                )
            }
        }

    @Test
    fun `given loadMorePokemons success, when pagination, then show screen with data`() =
        coroutinesTestRule.runTest {
            // Given
            coEvery {
                pokemonUseCase.execute(currentPage = currentPage)
            } returns Result.Success(data = POKEMON_LIST.toMutableList())

            // When
            viewModel.loadMorePokemons(page = currentPage)

            // Then
            coVerifyOrder {
                uiState.setupPaginating(isPaginating = true)
                pokemonUseCase.execute(currentPage = currentPage)
                uiState.showScreen(
                    pokemonList = POKEMON_LIST
                )
            }
        }

    @Test
    fun `given ErrorButtonAction and action, when onActionEvent, then retry get pokemon list`() =
        coroutinesTestRule.runTest {
            // Given
            coEvery {
                pokemonUseCase.execute()
            } returns Result.Success(data = POKEMON_LIST.toMutableList())
            // When
            viewModel.onActionEvent(
                action = PokemonListScreenAction.ErrorButtonAction
            )
            coVerifyOrder {
                uiState.showLoading()
                pokemonUseCase.execute()
                uiState.showScreen(
                    pokemonList = POKEMON_LIST,
                )
            }
        }

    @Test
    fun `given ErrorCloseButtonAction and action, when onActionEvent, then finish`() =
        coroutinesTestRule.runTest {

            // When
            viewModel.onActionEvent(
                action = PokemonListScreenAction.ErrorCloseButtonAction
            )
            coVerifyOrder {
                uiEvent.send(event = PokemonsListUiEvent.Event.Finish)
            }
        }

    @Test
    fun `given CloseButtonAction and action, when onActionEvent, then finish`() =
        coroutinesTestRule.runTest {

            // When
            viewModel.onActionEvent(
                action = PokemonListScreenAction.CloseButtonAction
            )
            coVerifyOrder {
                uiEvent.send(event = PokemonsListUiEvent.Event.Finish)
            }
        }

    // Helpers

    private companion object {
        val POKEMON_LIST = Pokemon.mockList()
    }
}