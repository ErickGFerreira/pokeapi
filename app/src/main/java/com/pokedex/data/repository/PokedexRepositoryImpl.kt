package com.pokedex.data.repository

import com.pokedex.data.api.PokedexApi
import com.pokedex.data.mapper.PokemonMapper.toPokemonDetail
import com.pokedex.data.mapper.PokemonMapper.toPokemonList
import com.pokedex.domain.model.PokemonDetail
import com.pokedex.domain.model.PokemonList
import com.pokedex.domain.repository.PokedexRepository
import javax.inject.Inject

class PokedexRepositoryImpl @Inject constructor(
    private val api: PokedexApi,
) : PokedexRepository {
    override suspend fun getPokemonList(): PokemonList =
        api.getPokemonList().toPokemonList()

    override suspend fun getPokemonDetail(url: String): PokemonDetail =
        api.getPokemonDetail(url).toPokemonDetail()
}
