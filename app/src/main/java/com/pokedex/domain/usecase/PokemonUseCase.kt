package com.pokedex.domain.usecase

import com.pokedex.domain.model.Pokemon
import com.pokedex.utils.safe.getOrThrow
import com.pokedex.utils.safe.safeRunDispatcher
import javax.inject.Inject

class PokemonUseCase @Inject constructor(
    private val getPokemonListUseCase: GetPokemonListUseCase,
    private val getPokemonDetailUseCase: GetPokemonDetailUseCase,
) {
    suspend fun execute() =
        safeRunDispatcher {
            val pokemonList = mutableListOf<Pokemon>()
            val pokemonAddrList = getPokemonListUseCase.execute().getOrThrow()
            pokemonAddrList.results.forEach {
                val detail = getPokemonDetailUseCase.execute(it.url).getOrThrow()
                pokemonList.add(
                    Pokemon(
                        name = it.name,
                        imageUrl = detail.sprites.frontDefault,
                        type = detail.type.first().type.name
                    )
                )
            }
            return@safeRunDispatcher pokemonList
        }
}