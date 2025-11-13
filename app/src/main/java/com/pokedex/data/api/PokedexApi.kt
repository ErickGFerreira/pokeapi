package com.pokedex.data.api

import com.pokedex.data.response.PokemonDetailResponse
import com.pokedex.data.response.PokemonsListResponse
import retrofit2.http.GET
import retrofit2.http.Url

interface PokedexApi {
    @GET("pokemon")
    suspend fun getPokemonList(): PokemonsListResponse

    @GET
    suspend fun getPokemonDetail(@Url url: String): PokemonDetailResponse
}