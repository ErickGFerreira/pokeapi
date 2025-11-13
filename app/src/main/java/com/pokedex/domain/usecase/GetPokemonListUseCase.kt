package com.pokedex.domain.usecase

import com.pokedex.domain.model.PokemonList
import com.pokedex.utils.safe.Result

fun interface GetPokemonListUseCase {
    suspend fun execute(): Result<PokemonList>
}
