package com.pokedex.domain.repository

import com.pokedex.data.response.PokemonsListResponse
import com.pokedex.domain.model.PokemonDetail
import com.pokedex.domain.model.PokemonList

interface PokedexRepository {
    suspend fun getPokemonList(): PokemonList

    suspend fun getPokemonDetail(url: String): PokemonDetail
}