package com.pokedex.domain.usecase

import com.pokedex.domain.model.Pokemon
import com.pokedex.utils.safe.getOrThrow
import com.pokedex.utils.safe.safeRunDispatcher
import javax.inject.Inject

class PokemonUseCase @Inject constructor(
    private val getPokemonListUseCase: GetPokemonListUseCase,
    private val getPokemonDetailUseCase: GetPokemonDetailUseCase,
) {
    suspend fun execute(currentPage: Int = 0) =
        safeRunDispatcher {
            val pokemonList = getPokemonListUseCase.execute(currentPage = currentPage)
                .getOrThrow().results.map { addr ->
                    val detail = getPokemonDetailUseCase.execute(addr.url).getOrThrow()
                    Pokemon(
                        id = detail.id,
                        name = addr.name,
                        imageUrl = detail.sprites.frontDefault,
                        type = detail.types.map {
                            it.type
                        }
                    )
                }
            return@safeRunDispatcher pokemonList
        }
}