package com.pokedex.data.repository

import com.pokedex.data.api.PokedexApi
import com.pokedex.data.response.PokemonDetailResponse
import com.pokedex.data.response.PokemonsListResponse
import com.pokedex.domain.model.PokemonDetail
import com.pokedex.domain.model.PokemonList
import com.pokedex.domain.repository.PokedexRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.net.SocketTimeoutException

class PokedexRepositoryImplTest {
    private val api = mockk<PokedexApi>(relaxed = true)
    private lateinit var repository: PokedexRepository

    @Before
    fun setUp() {
        repository = PokedexRepositoryImpl(api = api)
    }

    @Test
    fun `given success, when get getCardOptionsList, then return CREDIT_CARD_OPTIONS`() =
        runBlocking {
            // given
            mockGetPokemonListSucess()

            // when
            val result = repository.getPokemonList()

            // then
            coVerify {
                api.getPokemonList()
            }
            assertEquals(POKEMON_LIST, result)
        }

    @Test(expected = SocketTimeoutException::class)
    fun `given failure, when getCardOptionsList, then throws SocketTimeoutException`() =
        runBlocking<Unit> {
            // given
            mockGetPokemonListFailure()

            // when
            repository.getPokemonList()
        }

    @Test
    fun `given success, when getPokemonDetail, then get pokemon detail data`() =
        runBlocking {
            // given
            mockGetPokemonDetailSuccess()

            // when
            val result = repository.getPokemonDetail(url = "URL")

            // then
            coVerify {
                api.getPokemonDetail(url = "URL")
            }
            assertEquals(POKEMON_DETAIL, result)
        }

    @Test(expected = SocketTimeoutException::class)
    fun `given failure, when getPokemonDetail, then throws SocketTimeoutException`() =
        runBlocking<Unit> {
            // given
            mockGetPokemonDetailFailure()

            // when
            repository.getPokemonDetail(url = "URL")
        }

    // Helpers

    private fun mockGetPokemonListSucess() {
        coEvery {
            api.getPokemonList()
        } returns POKEMON_LIST_RESPONSE
    }

    private fun mockGetPokemonListFailure() {
        coEvery {
            api.getPokemonList()
        } throws SocketTimeoutException()
    }

    private fun mockGetPokemonDetailSuccess() {
        coEvery {
            api.getPokemonDetail("URL")
        } returns POKEMON_DETAIL_RESPONSE
    }

    private fun mockGetPokemonDetailFailure() {
        coEvery {
            api.getPokemonDetail("URL")
        } throws SocketTimeoutException()
    }

    private companion object {
        val POKEMON_LIST = PokemonList.mock()
        val POKEMON_DETAIL = PokemonDetail.mock()
        val POKEMON_LIST_RESPONSE = PokemonsListResponse.mock()
        val POKEMON_DETAIL_RESPONSE = PokemonDetailResponse.mock()

    }
}