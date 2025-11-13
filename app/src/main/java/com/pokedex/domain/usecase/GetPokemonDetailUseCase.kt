package com.pokedex.domain.usecase

import com.pokedex.domain.model.PokemonDetail
import com.pokedex.utils.safe.Result

fun interface GetPokemonDetailUseCase {
    suspend fun execute(url: String): Result<PokemonDetail>
}
