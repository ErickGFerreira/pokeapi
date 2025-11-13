package com.pokedex.domain.usecase

import com.pokedex.domain.model.Pokemon
import com.pokedex.domain.model.PokemonDetail
import com.pokedex.domain.model.PokemonList
import com.pokedex.utils.error.ErrorHandler
import com.pokedex.utils.safe.Result
import com.pokedex.utils.safe.getOrThrow
import com.pokedex.utils.safe.isFailure
import io.mockk.coEvery
import io.mockk.coVerifyOrder
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test


class PokemonUseCaseTest {
    private val getPokemonListUseCase: GetPokemonListUseCase = mockk()

    private val getPokemonDetailUseCase: GetPokemonDetailUseCase = mockk()

    private lateinit var useCase: PokemonUseCase

    @Before
    fun setup() {
        useCase =
            PokemonUseCase(
                getPokemonListUseCase = getPokemonListUseCase,
                getPokemonDetailUseCase = getPokemonDetailUseCase,
            )
    }

    @Test
    fun `given get pokemon data success, when execute, then return success pokemon list`() =
        runBlocking {
            // given
            mockGetPokemonListUseCaseSuccess()
            mockGetPushProvisioningDetailUseCaseSuccess()

            // when
            val result = useCase.execute()

            // then
            assertThat(result.getOrThrow()).isEqualTo(Pokemon.mockList())
            coVerifyOrder {
                getPokemonListUseCase.execute()
                getPokemonDetailUseCase.execute(url = "url")
            }
        }

    @Test
    fun `given get pokemon list fails, when execute, then return failure`() =
        runBlocking {
            // given
            mockGetPushProvisioningDetailUseCaseSuccess()
            mockGetPokemonListUseCaseSuccess(result = Result.Failure(error = ErrorHandler.timeOutError))

            // when
            val result = useCase.execute()

            // then
            assertThat(result.isFailure()).isTrue()
            coVerifyOrder {
                getPokemonListUseCase.execute()
            }
        }

    @Test
    fun `given get pokemon detail fails, when execute, then return failure`() =
        runBlocking {
            // given
            mockGetPokemonListUseCaseSuccess()
            mockGetPushProvisioningDetailUseCaseSuccess(result = Result.Failure(error = ErrorHandler.timeOutError))

            // when
            val result = useCase.execute()

            // then
            assertThat(result.isFailure()).isTrue()
            coVerifyOrder {
                getPokemonListUseCase.execute()
                getPokemonDetailUseCase.execute(url = "url")
            }
        }

    // Helpers

    private fun mockGetPokemonListUseCaseSuccess(
        result: Result<PokemonList> =
            Result.Success(
                data = POKEMON_LIST,
            ),
    ) {
        coEvery { getPokemonListUseCase.execute() } returns result
    }

    private fun mockGetPushProvisioningDetailUseCaseSuccess(
        result: Result<PokemonDetail> =
            Result.Success(
                data = POKEMON_DETAIL,
            ),
    ) {
        coEvery {
            getPokemonDetailUseCase.execute(
                url = "url"
            )
        } returns result
    }


    private companion object {
        val POKEMON_LIST = PokemonList.mock()
        val POKEMON_DETAIL = PokemonDetail.mock()
    }
}
