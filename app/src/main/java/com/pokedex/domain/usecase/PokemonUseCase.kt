package com.pokedex.domain.usecase

import com.pokedex.domain.model.Pokemon
import com.pokedex.domain.model.PokemonAddr
import com.pokedex.utils.safe.getOrThrow
import com.pokedex.utils.safe.safeRunDispatcher
import javax.inject.Inject

class PokemonUseCase @Inject constructor(
    private val getPokemonListUseCase: GetPokemonListUseCase,
    private val getPokemonDetailUseCase: GetPokemonDetailUseCase,
) {
    suspend fun execute() =
        safeRunDispatcher {
            val pokemonList = getPokemonListUseCase.execute().getOrThrow().results.map { addr ->
                val detail = getPokemonDetailUseCase.execute(addr.url).getOrThrow()
                Pokemon(
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